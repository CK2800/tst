package ui;

import gameobjects.Apple;
import gameobjects.Facade;
import gameobjects.Snake;
import gameobjects.constants.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener
{
    private final int screenWidth, screenHeight, unitSize, delay;
    private boolean showGrid, running;
    private Facade facade;
    private Apple apple;
    private Snake snake;
    private Random random;
    private Timer timer;
    private Direction direction = Direction.NONE;
    private SnakeKeyAdapter snakeKeyAdapter = new SnakeKeyAdapter();


    public GamePanel(int screenWidth, int screenHeight, int unitSize, boolean showGrid, int delay, Facade facade)
    {
        // Q&D let's be defensive.
        if(
                screenWidth < 600 ||
                screenHeight < 600 ||
                unitSize < 5 ||
                delay < 25) throw new IllegalArgumentException("GamePanel cannot be run under these circumstances!");
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.unitSize = unitSize;
        this.showGrid = showGrid;
        this.delay = delay;
        this.facade = facade;
        this.apple = facade.createApple();
        this.snake = facade.createSnake(3);

        random = new Random();


        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(snakeKeyAdapter);

        startGame();
    }

    private void newApple(Facade facade, Apple apple)
    {
        facade.moveAppleTo(
                apple,
                random.nextInt((int) screenWidth / unitSize)* unitSize,
                random.nextInt((int) screenHeight / unitSize)* unitSize);
    }

    public void startGame()
    {
        newApple(facade, apple);
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        if (showGrid)
            drawGrid(g);

        apple.draw(g, unitSize, unitSize);
        snake.draw(g, unitSize, unitSize);

    }

    private void drawGrid(Graphics g)
    {
        for(int i = 0; i < screenHeight / unitSize; i++)
        {
            g.drawLine(i * unitSize, 0, i * unitSize, screenHeight);
            g.drawLine(0, i* unitSize, screenWidth, i* unitSize);
        }
    }

    public void move()
    {
        facade.moveSnakeBy(snake, snakeKeyAdapter.getCurrentDirection(), unitSize);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

        System.out.println(running);
        if(running)
        {
            move();
            if (facade.isEatenBy(apple, snake))
            {
                newApple(facade, apple);
                facade.growSnake(snake, 1);
            }
            if (facade.snakeCollidesWithBody(snake))
            {
                System.out.println("Snake body hit");
                running = false;
            }

            if (facade.snakeCollidesWithBorder(snake, screenWidth, screenHeight))
            {
                System.out.println("BORDER HIT");
                running = false;
            }

        }
        repaint();
    }
}

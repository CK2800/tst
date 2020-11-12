package gameobjects.impl;

import gameobjects.Apple;
import gameobjects.Facade;
import gameobjects.Snake;
import gameobjects.constants.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FacadeImpl implements Facade
{
    public FacadeImpl() {

    }

    /**
     * Creates an Apple object placed in (0,0)
     * @return Apple
     */
    public Apple createApple()
    {
        return new AppleImpl();
    }

    /**
     * Creates a Snake object of the specified length. Snake head is placed in (0,0), all body parts outside the viewing area.
     * @param snakeLength 3 or above.
     * @return Snake
     */
    public Snake createSnake(int snakeLength)
    {
        if (snakeLength < 3) throw new IllegalArgumentException("Snake must have a length of 3 or above.");

        List<Point> snakePoints = new ArrayList<Point>();
        for(int i = 0; i < snakeLength; i++)
            snakePoints.add(new Point(i*-10,0));
        return new SnakeImpl(snakePoints);
    }

    @Override
    public void moveAppleTo(Apple apple, int x, int y)
    {
        apple.moveTo(x,y);
    }

    @Override
    public void moveSnakeBy(Snake snake, Direction direction, int pixels)
    {
        snake.moveBy(direction, pixels);
    }

    @Override
    public boolean isEatenBy(Apple apple, Snake snake)
    {
        return apple.collidesWith(snake);
    }

    @Override
    public boolean snakeCollidesWithBody(Snake snake)
    {
        return snake.collidesWith(snake);
    }


}

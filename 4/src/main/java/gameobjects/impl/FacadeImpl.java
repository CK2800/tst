package gameobjects.impl;

import gameobjects.Apple;
import gameobjects.Facade;
import gameobjects.Snake;
import gameobjects.constants.Border;
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
    public void growSnake(Snake snake, int length)
    {
        // defensive programming.
        if (length <= 0)
            throw new IllegalArgumentException("length must be greater than 0!");
        if (snake == null)
            throw new NullPointerException("snake is null.");
        snake.addBodyPart(length);
    }

    /**
     * Checks if snake is at either border of the playing area.     *
     * @param snake
     * @param width The placement of the right border, i.e. the width of the playing area.
     * @param height The placement of the bottom border, i.e. the height of the playing area.
     * @return
     */
    @Override
    public Border snakeCollidesWithBorder(Snake snake, int width, int height)
    {
        if (snake.hasHitBorder(Border.TOP, 0))
            return Border.TOP;
        if (snake.hasHitBorder(Border.LEFT, 0))
            return Border.LEFT;
        if(snake.hasHitBorder(Border.BOTTOM, height))
            return Border.BOTTOM;
        if (snake.hasHitBorder(Border.RIGHT, width))
            return Border.RIGHT;

        return Border.NONE;
    }

    /**
     * Wraps a snake around the playing field by moving its head to the opposite border.
     * @param snake
     * @param borderCrossed
     * @param width The width of the playing field.
     * @param height The height of the playing field.
     */
    @Override
    public void wrapSnake(Snake snake, Border borderCrossed, int width, int height) throws IllegalArgumentException
    {
        Point head = snake.getHead();
        Direction direction;
        int pixels;
        switch(borderCrossed)
        {
            case TOP:
                pixels = -height;
                direction = Direction.UP;
                break;

            case BOTTOM:
                pixels = -height;
                direction = Direction.DOWN;
                break;

            case LEFT:
                pixels = -width;
                direction = Direction.LEFT;
                break;

            case RIGHT:
                pixels = -width;
                direction = Direction.RIGHT;
                break;

            default:
                throw new IllegalArgumentException("borderCrossed argument is invalid.");

        }
        snake.moveBy(direction, pixels, true);
    }

    @Override
    public void moveAppleTo(Apple apple, int x, int y)
    {
        apple.moveTo(x,y);
    }

    @Override
    public boolean moveSnakeBy(Snake snake, Direction direction, int pixels)
    {
        return snake.moveBy(direction, pixels, false);
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

package gameobjects.impl;

import gameobjects.Collidable;
import gameobjects.Snake;
import gameobjects.constants.Direction;

import java.awt.*;
import java.util.List;

import static gameobjects.constants.Direction.*;

public class SnakeImpl implements Snake
{
    private List<Point> points;

    private SnakeImpl(){}
    public SnakeImpl(List<Point> points)
    {
        if (points == null || points.size() == 0) throw new IllegalArgumentException("points is empty."); // IAE unchecked
        this.points = points;
    }

    @Override
    public boolean hasCollidedWithBody()
    {
        return collidesWith(this);
    }

    @Override
    public boolean isGameWon(int maxLength)
    {
        if (maxLength <= 0) throw new IllegalArgumentException("maxLength must be greater than zero"); // IAE unchecked
        return maxLength <= points.size();
    }

    /**
     * A snake collides with another object, if its head has the same position.
     * @param other The other Collidable that the snake may collide with.
     * @return true if snakes head is at same position as other. False otherwise.
     */
    @Override
    public boolean collidesWith(Collidable other)
    {
        if (points.size() == 0)
            return false;
        // check if snakes head collides with other.
        Point head = points.get(0);
        int i = (this == other) ? 1 : 0;

        for(; i < other.getPoints().size(); i++)
            if (other.getPoints().get(i).equals(head))
                return true;

        return false;
    }

    @Override
    public List<Point> getPoints()
    {
        // never reveal the internal list.
        return List.copyOf(points);
    }



    @Override
    public void moveBy(Direction direction, int pixels)
    {
        // use private helper.
        moveBody();

        // clone the heads point since it is also referenced by first bodypart after head.
        Point head = (Point)points.get(0).clone();

        // update the head.
        switch(direction)
        {
            case UP:

                head.x -= pixels;
                break;
            case DOWN:
                head.x += pixels;
                break;
            case LEFT:
                head.y -= pixels;
                break;
            case RIGHT:
                head.y += pixels;
                break;
            default:
                throw new IllegalArgumentException("The supplied Direction is not supported.");
        }

        // update the head.
        points.set(0, head);
    }

    private void moveBody()
    {
        // update the body positions of snake.
        for(int i = points.size() - 1; i > 0; i--)
        {
            points.set(i, points.get(i-1));
        }
    }
}

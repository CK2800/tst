package gameobjects.impl;

import gameobjects.Collidable;
import gameobjects.Snake;

import java.awt.*;
import java.util.List;

public class SnakeImpl implements Snake
{
    private List<Point> points;

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
}

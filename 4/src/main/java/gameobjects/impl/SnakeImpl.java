package gameobjects.impl;

import gameobjects.Collidable;
import gameobjects.Snake;
import gameobjects.constants.Border;
import gameobjects.constants.Direction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static gameobjects.constants.Direction.*;

public class SnakeImpl implements Snake
{
    private List<Point> points;
    private Direction currentDirection = NONE;

    private SnakeImpl(){}
    public SnakeImpl(List<Point> points)
    {
        if (points == null || points.size() == 0) throw new IllegalArgumentException("points is empty."); // IAE unchecked
        this.points = new ArrayList<Point>();

        // copy the supplied Point in this list.
        for(Point point : points)
            this.points.add(new Point(point.x, point.y));
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

        List<Point> otherPoints = other.getPoints();

        for(; i < otherPoints.size(); i++)
            if (otherPoints.get(i).equals(head))
                return true;

        return false;
    }

    /**
     * Returns an immutable copy of the current list of points.
     * @return
     */
    @Override
    public List<Point> getPoints()
    {
        // never reveal the internal list.
        return List.copyOf(points);
    }



    @Override
    public boolean moveBy(Direction direction, int pixels, boolean headOnly)
    {
//        if (pixels < 0)
//            throw new IllegalArgumentException("Pixels must be greater than zero.");
        switch(direction)
        {
            case UP:
                if (currentDirection != DOWN)
                {
                    doMoveBy(direction, pixels, headOnly);
                    currentDirection = direction;
                    return true;
                }
                return false;

            case DOWN:
                if (currentDirection != UP)
                {
                    doMoveBy(direction, pixels, headOnly);
                    currentDirection = direction;
                    return true;
                }
                return false;

            case LEFT:
                if (currentDirection != RIGHT)
                {
                    doMoveBy(direction, pixels, headOnly);
                    currentDirection = direction;
                    return true;
                }
                return false;

            case RIGHT:
                if (currentDirection != LEFT)
                {
                    doMoveBy(direction, pixels, headOnly);
                    currentDirection = direction;
                    return true;
                }
                return false;
            default:
                return false;
        }
    }


    private void doMoveBy(Direction direction, int pixels, boolean headOnly)
    {
        // use private helper.
        if (!headOnly)
            moveBody();

        // clone the heads point since it is also referenced by first bodypart after head.
        Point head = (Point)points.get(0).clone();

        // update the head.
        switch(direction)
        {
            case UP:

                head.y -= pixels;
                break;
            case DOWN:
                head.y += pixels;
                break;
            case LEFT:
                head.x -= pixels;
                break;
            case RIGHT:
                head.x += pixels;
                break;
            case NONE:
                break;
            default:
                throw new IllegalArgumentException("The supplied Direction is not supported.");
        }

        // update the head.
        points.set(0, head);
    }

    @Override
    public boolean hasHitBorder(Border border, int threshold)
    {
        switch(border)
        {
            case TOP:
                return this.points.get(0).y < threshold;
            case BOTTOM:
                return this.points.get(0).y >= threshold;
            case LEFT:
                return this.points.get(0).x < threshold;
            case RIGHT:
                return this.points.get(0).x >= threshold;
            default:
                throw new IllegalArgumentException("The supplied value of type Border is not supported." + border.toString());
        }
    }

    @Override
    public void addBodyPart(int count)
    {
        // Put new bodyparts in the rear end of snake.
        Point rear = (Point)points.get(points.size()-1).clone();

        for(int i = 0; i < count; i++)
            points.add(new Point(rear.x, rear.y));
    }

//    @Override
//    public void moveHeadTo(int x, int y)
//    {
//        if (x < 0 || y < 0)
//            throw new IllegalArgumentException("The provided values for x and y must both be zero or larger.");
//        Point head = points.get(0);
//        head.x = x;
//        head.y = y;
//    }

    /**
     * Gets a copy of the head position.
     * @return Point
     */
    public Point getHead()
    {
        // we do NOT want do expose the internal head Point.
        return (Point)points.get(0).clone();
    }

    /**
     * Total length of the snake.
     * @return int
     */
    @Override
    public int getLength()
    {
        return points.size();
    }

    private void moveBody()
    {
        // update the body positions of snake.
        for(int i = points.size() - 1; i > 0; i--)
        {
            points.set(i, points.get(i-1));
        }
    }

    @Override
    public void draw(Graphics g, int width, int height)
    {
        g.setColor(Color.green);
        for(Point point : points)
        {
            g.fillRect(point.x, point.y, width,height);
        }
    }
}

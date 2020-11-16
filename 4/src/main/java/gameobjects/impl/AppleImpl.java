package gameobjects.impl;

import gameobjects.Apple;
import gameobjects.Collidable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AppleImpl implements Apple
{
    private Point point = new Point();
    private final ArrayList<Point> list = new ArrayList<Point>(1);

    @Override
    public boolean collidesWith(Collidable other)
    {
        List<Point> otherPoints = other.getPoints();
        for(int i = 0; i < otherPoints.size(); i++)
        {
            if (otherPoints.get(i).equals(point))
                return true;
        }
        return false;
    }

    @Override
    public List<Point> getPoints()
    {
        list.clear();
        list.add((Point)point.clone());
        return list;

    }

    @Override
    public void moveTo(int x, int y)
    {
        point.move(x, y);
    }

    @Override
    public void moveTo(Point p)
    {
        point.x = p.x;
        point.y = p.y;
    }

    @Override
    public void draw(Graphics g, int width, int height)
    {
        g.setColor(Color.red);
        g.fillOval(this.point.x, this.point.y, width, height);
    }
}

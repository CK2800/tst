package gameobjects;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Apple implements Collidable
{
    private Point point = new Point();
    private final ArrayList<Point> list = new ArrayList<Point>(1);

    @Override
    public boolean collidesWith(Collidable other)
    {
        for(int i = 0; i < other.getPoints().size(); i++)
        {
            if (other.getPoints().get(i).equals(point))
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

    public void move(int x, int y)
    {
        point.move(x, y);
    }

    public void moveTo(Point p)
    {
        point.x = p.x;
        point.y = p.y;
    }
}

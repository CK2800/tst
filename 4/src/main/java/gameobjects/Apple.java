package gameobjects;

import java.awt.*;

public interface Apple extends Collidable
{
    void move(int x, int y);

    void moveTo(Point p);
}

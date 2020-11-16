package gameobjects;

import gameobjects.impl.Drawable;

import java.awt.*;

public interface Apple extends Collidable, Drawable
{
    void moveTo(int x, int y);

    void moveTo(Point p);
}

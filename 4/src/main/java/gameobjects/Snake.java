package gameobjects;

import gameobjects.constants.Direction;
import gameobjects.impl.Drawable;

import java.awt.*;
import java.util.List;

public interface Snake extends Collidable, Drawable
{
    boolean isGameWon(int maxLength);

    List<Point> getPoints();

    void moveBy(Direction direction, int pixels);
}

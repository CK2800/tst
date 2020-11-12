package gameobjects;

import gameobjects.constants.Border;
import gameobjects.constants.Direction;
import gameobjects.impl.Drawable;

import java.awt.*;
import java.util.List;

public interface Snake extends Collidable, Drawable
{
    boolean isGameWon(int maxLength);

    List<Point> getPoints();

    boolean moveBy(Direction direction, int pixels);

    boolean hasHitBorder(Border border, int threshold);
}

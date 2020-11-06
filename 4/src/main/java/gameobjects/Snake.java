package gameobjects;

import gameobjects.constants.Direction;
import java.awt.*;
import java.util.List;

public interface Snake extends Collidable
{
    boolean hasCollidedWithBody();

    boolean isGameWon(int maxLength);

    List<Point> getPoints();

    void moveBy(Direction direction, int pixels);
}

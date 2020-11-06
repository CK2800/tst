package gameobjects;

import java.awt.*;
import java.util.List;

public interface Snake extends Collidable
{
    boolean hasCollidedWithBody();

    boolean isGameWon(int maxLength);

    List<Point> getPoints();
}

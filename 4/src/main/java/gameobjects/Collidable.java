package gameobjects;

import java.awt.*;
import java.util.List;

public interface Collidable
{
    boolean collidesWith(Collidable other);
    List<Point> getPoints();
}

package gameobjects;

import gameobjects.constants.Border;
import gameobjects.constants.Direction;

import java.awt.*;

public interface Facade
{
    void moveAppleTo(Apple apple, int x, int y);

    void moveSnakeBy(Snake snake, Direction direction, int pixels);

    boolean isEatenBy(Apple apple, Snake snake);

    boolean snakeCollidesWithBody(Snake snake);

    Apple createApple();

    Snake createSnake(int snakeLength);

    void growSnake(Snake snake, int length);

    boolean snakeCollidesWithBorder(Snake snake, int width, int height);

    void wrapSnake(Snake snake, Border borderCrossed, int width, int height);
}

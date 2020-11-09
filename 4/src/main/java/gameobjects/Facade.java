package gameobjects;

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
}

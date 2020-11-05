package unit.gameobjects;

import gameobjects.Apple;
import gameobjects.Snake;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppleTest
{
    Snake snake;
    Apple apple;
    List<Point> points = new ArrayList<Point>(2);
    @BeforeAll
    public void setup()
    {
        points.add(new Point(5,10));
        points.add(new Point(10,20));
        snake = new Snake(points);
    }

    @BeforeEach
    public void beforeEach()
    {
        apple = new Apple(); // apple at (0,0)
    }

    @Test
    public void mustReturnTrueIfSnakeCollidesWithApple() {
        // Arrange
        var expected = true;
        apple.move(5,10);

        // Act
        var result = snake.collidesWith(apple);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseIfSnakeDoesNotCollideWithApple() {
        // Arrange
        var expected = false;

        // Act
        var result = snake.collidesWith(apple);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnTrueIfAppleCollidesWithSnake() {
        // Arrange
        var expected = true;
        apple.move(5,10);
        // Act
        var result = apple.collidesWith(snake);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseIfAppleDoesNotCollideWithSnake() {
        // Arrange
        var expected = false;

        // Act
        var result = apple.collidesWith(snake);

        // Assert
        assertEquals(expected, result);
    }
}

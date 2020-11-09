package unit.gameobjects;

import gameobjects.Apple;
import gameobjects.Snake;
import gameobjects.impl.AppleImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppleImplTest
{
    // UUT
    Apple apple;

    @Mock
    Snake snakeMock = mock(Snake.class);
    List<Point> snakePoints = new ArrayList<Point>(2);

    @BeforeAll
    public void setup()
    {
        snakePoints.add(new Point(5,10));
        snakePoints.add(new Point(10,20));
        when(snakeMock.getPoints()).thenReturn(snakePoints);
    }

    @BeforeEach
    public void beforeEach()
    {
        apple = new AppleImpl(); // apple at (0,0)
    }

    @Test
    public void mustReturnTrueIfAppleCollidesWithSnake() {
        // Arrange
        var expected = true;
        apple.moveTo(5,10);

        // Act
        var result = apple.collidesWith(snakeMock);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseIfAppleDoesNotCollideWithSnake() {
        // Arrange
        var expected = false;

        // Act
        var result = apple.collidesWith(snakeMock);

        // Assert
        assertEquals(expected, result);
    }
}

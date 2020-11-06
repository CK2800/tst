package unit.gameobjects;

import gameobjects.Apple;
import gameobjects.Snake;
import gameobjects.impl.SnakeImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SnakeImplTest
{
    // UUT
    Snake snake;

    List<Point> pointList;
    @Mock
    Apple appleMock = mock(Apple.class);
    List<Point> applePoints = new ArrayList<Point>(1);
    @BeforeAll
    public void beforeAll()
    {
        applePoints.add(new Point(6,7));
        when(appleMock.getPoints()).thenReturn(applePoints);
    }

    @BeforeEach
    public void beforeEach()
    {
        pointList = new ArrayList<Point>();
    }

    @Test
    public void mustReturnTrueWhenSnakeCollidesWithBody() {
        // Arrange
        var expected = true;
        pointList.add(new Point(15,10));
        pointList.add(new Point(15,10));
        snake = new SnakeImpl(pointList);
        
        // Act
        var result = snake.collidesWith(snake);
        
        // Assert
        assertEquals(expected, result);
    }
    
    @Test
    public void mustReturnFalseWhenSnakeDoesNotCollideWithBody() {
        // Arrange
        var expected = false;
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        snake = new SnakeImpl(pointList);
        
        // Act
        var result = snake.collidesWith(snake);
        
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustThrowWhenSnakeIsInitiatedWithEmptyPointsList() {
        // Arrange
        assertThrows(IllegalArgumentException.class, () -> new SnakeImpl(new ArrayList<>()));

    }

    @Test
    public void mustReturnTrueWhenSnakeFillsWholeScreen() {
        // Arrange
        var expected = true;
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        snake = new SnakeImpl(pointList);

        // Act
        var result = snake.isGameWon(6);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseWhenSnakeDoesNotFillWholeScreen() {
        // Arrange
        var expected = false;
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        snake = new SnakeImpl(pointList);
        // Act
        var result = snake.isGameWon(100);

        // Assert
        assertEquals(expected, result);
    }

        @Test
    public void mustReturnTrueIfSnakeCollidesWithApple() {
        // Arrange
        var expected = true;
        pointList.add(0, new Point(6,7)); // snakes head at apples position.
        snake = new SnakeImpl(pointList);

        // Act
        var result = snake.collidesWith(appleMock);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseIfSnakeDoesNotCollideWithApple() {
        // Arrange
        var expected = false;
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));

        // Act
        var result = snake.collidesWith(appleMock);

        // Assert
        assertEquals(expected, result);
    }
}

package unit.gameobjects;

import gameobjects.Apple;
import gameobjects.Snake;
import gameobjects.constants.Direction;
import gameobjects.impl.SnakeImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SnakeImplTest
{
    // UUT
    Snake snake;

    List<Point> originalPoints;
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
        // reset the snake.
        snake = null;
        originalPoints = new ArrayList<Point>();
    }

    @Test
    public void mustReturnTrueWhenSnakeCollidesWithBody() {
        // Arrange
        var expected = true;
        originalPoints.add(new Point(15,10));
        originalPoints.add(new Point(15,10));
        snake = new SnakeImpl(originalPoints);
        
        // Act
        var result = snake.collidesWith(snake);
        
        // Assert
        assertEquals(expected, result);
    }
    
    @Test
    public void mustReturnFalseWhenSnakeDoesNotCollideWithBody() {
        // Arrange
        var expected = false;
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        snake = new SnakeImpl(originalPoints);
        
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
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        snake = new SnakeImpl(originalPoints);

        // Act
        var result = snake.isGameWon(6);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseWhenSnakeDoesNotFillWholeScreen() {
        // Arrange
        var expected = false;
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        snake = new SnakeImpl(originalPoints);
        // Act
        var result = snake.isGameWon(100);

        // Assert
        assertEquals(expected, result);
    }

        @Test
    public void mustReturnTrueIfSnakeCollidesWithApple() {
        // Arrange
        var expected = true;
        originalPoints.add(0, new Point(6,7)); // snakes head at apples position.
        snake = new SnakeImpl(originalPoints);

        // Act
        var result = snake.collidesWith(appleMock);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseIfSnakeDoesNotCollideWithApple() {
        // Arrange
        var expected = false;
        originalPoints.add(new Point(0,0));
        originalPoints.add(new Point(10,10));
        snake = new SnakeImpl(originalPoints);

        // Act
        var result = snake.collidesWith(appleMock);

        // Assert
        assertEquals(expected, result);
    }
    @Test
    public void mustIncrementHeadXWhenMovingDown() {
        // Arrange
        var expected = 25;
        originalPoints.add(new Point(5,10)); // head
        originalPoints.add(new Point(4,10)); // body part
        snake = new SnakeImpl(originalPoints);

        // Act
        snake.moveBy(Direction.DOWN, 20);
        var result = snake.getPoints().get(0).x;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustDecrementHeadXWhenMovingUp() {
        // Arrange
        var expected = 5;
        originalPoints.add(new Point(22, 10));
        originalPoints.add(new Point(-50,700));
        snake = new SnakeImpl(originalPoints);

        // Act
        snake.moveBy(Direction.UP, 17);
        var result = snake.getPoints().get(0).x;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustDecrementHeadYWhenMovingLeft() {
        // Arrange
        var expected = 12;
        originalPoints.add(new Point(22,24));
        originalPoints.add(new Point(44,44));
        originalPoints.add(new Point(4,4));
        snake = new SnakeImpl(originalPoints);

        // Act
        snake.moveBy(Direction.LEFT, 12);
        var result = snake.getPoints().get(0).y;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustIncrementHeadYWhenMovingRight() {
        // Arrange
        var expected = 50;
        originalPoints.add(new Point(0,0));
        snake = new SnakeImpl(originalPoints);

        // Act
        snake.moveBy(Direction.RIGHT, 50);
        var result = snake.getPoints().get(0).y;

        // Assert
        assertEquals(expected, result);
    }


    @Test
    public void mustUpdateBodyPartsWhenMoving() {
        // Arrange
        originalPoints.add(new Point(25,225)); // head
        originalPoints.add(new Point(2,25)); // body part
        originalPoints.add(new Point(5,25)); // body part
        originalPoints.add(new Point(2,25)); // body part
        originalPoints.add(new Point(25,25)); // body part
        originalPoints.add(new Point(30,30)); // body part
        snake = new SnakeImpl(originalPoints);

        List<Point> newPoints;

        // Act
        snake.moveBy(Direction.RIGHT, 50);
        newPoints = snake.getPoints();

        // Assert
        for(int i = newPoints.size()-1; i > 0; i--)
            assertTrue(newPoints.get(i).equals(originalPoints.get(i-1)));
    }
}

package unit.gameobjects;

import gameobjects.Apple;
import gameobjects.Snake;
import gameobjects.constants.Border;
import gameobjects.constants.Direction;
import gameobjects.impl.SnakeImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SnakeImplTest
{
    // UUT
    Snake snake;

    List<Point> initialPoints;
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
        initialPoints = new ArrayList<Point>();
    }

    @Test
    public void mustReturnTrueWhenSnakeCollidesWithBody() {
        // Arrange
        var expected = true;
        initialPoints.add(new Point(15,10));
        initialPoints.add(new Point(15,10));
        snake = new SnakeImpl(initialPoints);
        
        // Act
        var result = snake.collidesWith(snake);
        
        // Assert
        assertEquals(expected, result);
    }
    
    @Test
    public void mustReturnFalseWhenSnakeDoesNotCollideWithBody() {
        // Arrange
        var expected = false;
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        snake = new SnakeImpl(initialPoints);
        
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
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        snake = new SnakeImpl(initialPoints);

        // Act
        var result = snake.isGameWon(6);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseWhenSnakeDoesNotFillWholeScreen() {
        // Arrange
        var expected = false;
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        snake = new SnakeImpl(initialPoints);
        // Act
        var result = snake.isGameWon(100);

        // Assert
        assertEquals(expected, result);
    }

        @Test
    public void mustReturnTrueIfSnakeCollidesWithApple() {
        // Arrange
        var expected = true;
        initialPoints.add(0, new Point(6,7)); // snakes head at apples position.
        snake = new SnakeImpl(initialPoints);

        // Act
        var result = snake.collidesWith(appleMock);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnFalseIfSnakeDoesNotCollideWithApple() {
        // Arrange
        var expected = false;
        initialPoints.add(new Point(0,0));
        initialPoints.add(new Point(10,10));
        snake = new SnakeImpl(initialPoints);

        // Act
        var result = snake.collidesWith(appleMock);

        // Assert
        assertEquals(expected, result);
    }
    @Test
    public void mustIncrementHeadYWhenMovingDown() {
        // Arrange
        var amountToMove = 20;
        var initialY = 10;
        var expected = initialY + amountToMove;
        initialPoints.add(new Point(5,initialY)); // head
        initialPoints.add(new Point(4,initialY)); // body part
        snake = new SnakeImpl(initialPoints);

        // Act
        snake.moveBy(Direction.DOWN, amountToMove);
        var result = snake.getPoints().get(0).y;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustDecrementHeadYWhenMovingUp() {
        // Arrange
        var amountToMove = 17;
        var initialY = 22;
        var expected = initialY - amountToMove;
        initialPoints.add(new Point(22, initialY));
        initialPoints.add(new Point(-50,initialY));
        snake = new SnakeImpl(initialPoints);

        // Act
        snake.moveBy(Direction.UP, amountToMove);
        var result = snake.getPoints().get(0).y;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustDecrementHeadXWhenMovingLeft() {
        // Arrange
        var amountToMove = 12;
        var initialX = 24;
        var expected = initialX - amountToMove;
        initialPoints.add(new Point(initialX,24));
        initialPoints.add(new Point(initialX,44));
        initialPoints.add(new Point(initialX,4));
        snake = new SnakeImpl(initialPoints);

        // Act
        snake.moveBy(Direction.LEFT, amountToMove);
        var result = snake.getPoints().get(0).x;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustIncrementHeadXWhenMovingRight() {
        // Arrange
        var amountToMove = 50;
        var initialX = 10;
        var expected = initialX + amountToMove;
        initialPoints.add(new Point(initialX,0));
        snake = new SnakeImpl(initialPoints);

        // Act
        snake.moveBy(Direction.RIGHT, amountToMove);
        var result = snake.getPoints().get(0).x;

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustUpdateBodyPartsWhenMoving() {
        // Arrange
        initialPoints.add(new Point(25,225)); // head
        initialPoints.add(new Point(2,25)); // body part
        initialPoints.add(new Point(5,25)); // body part
        initialPoints.add(new Point(2,25)); // body part
        initialPoints.add(new Point(25,25)); // body part
        initialPoints.add(new Point(30,30)); // body part
        snake = new SnakeImpl(initialPoints);

        List<Point> newPoints;

        // Act
        snake.moveBy(Direction.RIGHT, 50);
        newPoints = snake.getPoints();

        // Assert
        for(int i = newPoints.size()-1; i > 0; i--)
            assertTrue(newPoints.get(i).equals(initialPoints.get(i-1)));
    }

    @Test
    public void mustReturnTrueWhenTopBorderIsHit() {
        // Arrange
        var expected = true;
        initialPoints.add(new Point(0,-5));
        snake = new SnakeImpl(initialPoints);

        // Act
        var result = snake.hasHitBorder(Border.TOP, 0);

        // Assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("setTurnArgs")
    public void mustReturnFalseIfTurning180(Direction currentDirection, Direction newDirection, boolean expected) {
        // Arrange
        initialPoints.add(new Point(5,5));
        snake = new SnakeImpl(initialPoints);

        // Act
        snake.moveBy(currentDirection, 200);
        var result = snake.moveBy(newDirection, 200);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream setTurnArgs()
    {
        return Stream.of(
                Arguments.of(Direction.UP, Direction.DOWN, false),
                Arguments.of(Direction.UP, Direction.LEFT, true),
                Arguments.of(Direction.UP, Direction.RIGHT, true),
                Arguments.of(Direction.UP, Direction.UP, true),
                Arguments.of(Direction.DOWN, Direction.DOWN, true),
                Arguments.of(Direction.DOWN, Direction.LEFT, true),
                Arguments.of(Direction.DOWN, Direction.RIGHT, true),
                Arguments.of(Direction.DOWN, Direction.UP, false),
                Arguments.of(Direction.LEFT, Direction.DOWN, true),
                Arguments.of(Direction.LEFT, Direction.LEFT, true),
                Arguments.of(Direction.LEFT, Direction.RIGHT, false),
                Arguments.of(Direction.LEFT, Direction.UP, true),
                Arguments.of(Direction.RIGHT, Direction.DOWN, true),
                Arguments.of(Direction.RIGHT, Direction.LEFT, false),
                Arguments.of(Direction.RIGHT, Direction.RIGHT, true),
                Arguments.of(Direction.RIGHT, Direction.UP, true)
        );
    }

    @ParameterizedTest
    @MethodSource("setBorderArgs")
    public void mustReturnTrueWhenBordersAreHit(Point head, Border border, int threshold, boolean expected) {
        // Arrange
        initialPoints.add(head);
        snake = new SnakeImpl(initialPoints);

        // Act
        boolean result = snake.hasHitBorder(border, threshold);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream setBorderArgs()
    {
        return Stream.of(
                Arguments.of(new Point(0,-1), Border.TOP, 0, true),
                Arguments.of(new Point(0,0), Border.TOP, 0, false),
                Arguments.of(new Point(0,801), Border.BOTTOM, 800, true),
                Arguments.of(new Point(0,800), Border.BOTTOM, 800, false),
                Arguments.of(new Point(-1,217), Border.LEFT, 0, true),
                Arguments.of(new Point(0,217), Border.LEFT, 0, false),
                Arguments.of(new Point(1025,217), Border.RIGHT, 1024, true),
                Arguments.of(new Point(1024,217), Border.RIGHT, 1024, false)
        );
    }
}

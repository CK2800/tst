package unit.gameobjects;

import gameobjects.Snake;
import org.junit.jupiter.api.*;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
public class SnakeTest
{
    Snake snake;
    List<Point> pointList;
    @BeforeEach
    public void setup()
    {
        pointList = new ArrayList<Point>();
    }

    @Test
    public void mustReturnTrueWhenSnakeHasCollidesWithBody() {
        // Arrange
        var expected = true;
        pointList.add(new Point(15,10));
        pointList.add(new Point(15,10));
        snake = new Snake(pointList);
        
        // Act
        var result = snake.collidesWith(snake);
        
        // Assert
        assertEquals(expected, result);
    }
    
    @Test
    public void mustReturnFalseWhenSnakeHasNotCollidedWithBody() {
        // Arrange
        var expected = false;
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        snake = new Snake(pointList);

        
        // Act
        var result = snake.collidesWith(snake);
        
        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustThrowWhenSnakeIsInitiatedWithEmptyPointsList() {
        // Arrange
        assertThrows(IllegalArgumentException.class, () -> new Snake(new ArrayList<>()));

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
        snake = new Snake(pointList);

        // Act
        var result = snake.isGameWon(6);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnTrueWhenSnakeDoesNotFillWholeScreen() {
        // Arrange
        var expected = false;
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        pointList.add(new Point(0,0));
        pointList.add(new Point(10,10));
        snake = new Snake(pointList);
        // Act
        var result = snake.isGameWon(100);

        // Assert
        assertEquals(expected, result);
    }
}

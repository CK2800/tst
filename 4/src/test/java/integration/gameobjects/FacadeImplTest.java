package integration.gameobjects;

import gameobjects.Apple;
import gameobjects.Facade;
import gameobjects.Snake;
import gameobjects.constants.Border;
import gameobjects.constants.Direction;
import gameobjects.impl.FacadeImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;

import java.awt.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class FacadeImplTest
{
    // UUT
    Facade facade = new FacadeImpl();

    // DOC
    @Mock
    Apple appleMock = mock(Apple.class);
    @Mock
    Snake snakeMock = mock(Snake.class);


    @Test
    public void mustMoveApple() {

        // Act
        facade.moveAppleTo(appleMock, 15,25);

        // Assert
        verify(appleMock).moveTo(15,25);
    }

    @Test
    public void mustMoveSnake() {

        // Act
        facade.moveSnakeBy(snakeMock, Direction.RIGHT, 5);

        // Assert
        verify(snakeMock).moveBy(Direction.RIGHT,5, false);
    }

    @Test
    public void mustGrowSnake() {
        // Act
        facade.growSnake(snakeMock, 10);

        // Assert
        verify(snakeMock).addBodyPart(10);
    }

    @Test
    public void mustCheckIfAppleCollidesWithSnake() {
        // Act
        boolean appleEaten = facade.isEatenBy(appleMock, snakeMock);

        // Assert
        verify(appleMock).collidesWith(snakeMock);
    }

    @Test
    public void mustCheckIfSnakeCollidesWithItself() {
        // Act
        boolean snakeBodyCollision = facade.snakeCollidesWithBody(snakeMock);

        // Assert
        verify(snakeMock).collidesWith(snakeMock);

    }

    @ParameterizedTest
    @MethodSource("snakeBorderHits")
    public void mustReturnBorderWhenRealSnakeHitsBorder(Direction direction, int pixels, int threshold, Border expected) {
        // Arrange
        Snake realSnake = facade.createSnake(3); // snake head @ (0,0)
        realSnake.moveBy(direction, pixels, false);
        // Act
        var result = facade.snakeCollidesWithBorder(realSnake, threshold, threshold);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream snakeBorderHits()
    {
        return Stream.of(
                Arguments.of(Direction.LEFT, 1, 0, Border.LEFT),
                Arguments.of(Direction.RIGHT, 1025, 1024, Border.RIGHT),
                Arguments.of(Direction.UP, 1, 0, Border.TOP),
                Arguments.of(Direction.DOWN, 801, 800, Border.BOTTOM)
        );
    }

    @Test
    public void mustCheckIfBorderCollisionIsDoneForAllBorders() {
        // Act
        var result = facade.snakeCollidesWithBorder(snakeMock, 1024, 800);

        // Assert
        verify(snakeMock, times(4)).hasHitBorder(any(Border.class), anyInt());
    }

    @Test
    public void mustWrapSnakeAfterHittingTopBorder() {
        // Arrange
        int width = 800, height = 800;
        when(snakeMock.getHead()).thenReturn(new Point(0,-50)); // head 50 pixels above top border.

        // Act
        facade.wrapSnake(snakeMock, Border.TOP, width, height);

        // Assert
        verify(snakeMock).moveBy(Direction.UP, -800, true);
    }

    @Test
    public void mustWrapSnakeAfterHittingBottomBorder() {
        // Arrange
        int width = 500, height = 800;
        when(snakeMock.getHead()).thenReturn(new Point(0, 850)); // head 50 pixels below bottom border.

        // Act
        facade.wrapSnake(snakeMock, Border.BOTTOM, width, height);

        // Assert
        verify(snakeMock).moveBy(Direction.DOWN, -800, true);
    }

    @Test
    public void mustWrapSnakeAfterHittingLeftBorder() {
        // Arrange
        int width = 600, height=600;
        when(snakeMock.getHead()).thenReturn(new Point(-75, 200)); // head 75 pixels left of left border.

        // Act
        facade.wrapSnake(snakeMock, Border.LEFT, width, height);

        // Assert
        verify(snakeMock).moveBy(Direction.LEFT, -600, true);
    }

    @Test
    public void mustWrapSnakeAfterHittingRightBorder() {
        // Arrange
        int width = 275, height = 100;
        when(snakeMock.getHead()).thenReturn(new Point(280, 20)); // head 5 pixels right of right border.

        // Act
        facade.wrapSnake(snakeMock, Border.RIGHT, width, height);

        // Assert
        verify(snakeMock).moveBy(Direction.RIGHT, -275, true);
    }
}

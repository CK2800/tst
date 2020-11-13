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
        verify(snakeMock).moveBy(Direction.RIGHT,5);
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
    public void mustReturnTrueWhenRealSnakeHitsBorder(Direction direction, int pixels, int threshold, boolean expected) {
        // Arrange
        Snake realSnake = facade.createSnake(3); // snake head @ (0,0)
        realSnake.moveBy(direction, pixels);
        // Act
        var result = facade.snakeCollidesWithBorder(realSnake, threshold, threshold);

        // Assert
        assertEquals(expected, result);
    }

    private static Stream snakeBorderHits()
    {
        return Stream.of(
                Arguments.of(Direction.LEFT, 1, 0, true),
                Arguments.of(Direction.RIGHT, 1025, 1024, true),
                Arguments.of(Direction.UP, 1, 0, true),
                Arguments.of(Direction.DOWN, 801, 800, true)
        );
    }

    @Test
    public void mustCheckIfBorderCollisionIsDoneForAllBorders() {
        // Act
        var result = facade.snakeCollidesWithBorder(snakeMock, 1024, 800);

        // Assert
        verify(snakeMock, times(4)).hasHitBorder(any(Border.class), anyInt());
    }
}

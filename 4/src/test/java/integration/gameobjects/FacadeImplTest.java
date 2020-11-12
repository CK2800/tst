package integration.gameobjects;

import gameobjects.Apple;
import gameobjects.Facade;
import gameobjects.Snake;
import gameobjects.constants.Direction;
import gameobjects.impl.FacadeImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
}

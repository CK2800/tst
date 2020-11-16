package unit.ui;

import gameobjects.constants.Direction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.SnakeKeyAdapter;

import java.awt.event.KeyEvent;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakeKeyAdapterTest
{
    // UUT
    SnakeKeyAdapter snakeKeyAdapter = new SnakeKeyAdapter();

    @ParameterizedTest
    @MethodSource("setKeyCodeArgs")
    public void mustConvertKeyConstantsToDirections(int keyCode, Direction expected) {

        // Act
        snakeKeyAdapter.setCurrentDirection(keyCode);
        Direction result = snakeKeyAdapter.getCurrentDirection();

        // Assert
        assertEquals(expected, result);
    }

    private static Stream setKeyCodeArgs()
    {
        return Stream.of(
                Arguments.of(KeyEvent.VK_RIGHT, Direction.RIGHT),
                Arguments.of(KeyEvent.VK_LEFT, Direction.LEFT),
                Arguments.of(KeyEvent.VK_UP, Direction.UP),
                Arguments.of(KeyEvent.VK_DOWN, Direction.DOWN)
        );
    }
}

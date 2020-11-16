package unit.gameobjects;

import gameobjects.Stage;
import gameobjects.impl.StageImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StageTest
{
//    @Mock
//    Stage stage;

    @Test
    public void mustCreateStageOfSizeWhenWidthAndHeightIsValid() {
        // Arrange
        var expected = 35;
        Stage stage = new StageImpl(5,7);

        // Act
        var result = stage.getSize();
        
        // Assert
        assertEquals(expected, result);
    }
    @Test
    public void mustThrowWhenWidthIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new StageImpl(StageImpl.MIN-1,StageImpl.MIN));
    }

    @Test
    public void mustThrowWhenHeightIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new StageImpl(StageImpl.MAX,StageImpl.MAX+1));
    }

    @Test
    public void mustThrowWhenWidthAndHeightIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new StageImpl(StageImpl.MIN-100,StageImpl.MAX*50));
    }
}

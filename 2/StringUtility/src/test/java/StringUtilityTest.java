import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilityTest
{
    /*@Test
    public void mustReturnABCWhenInputIsaBc() {
        // Arrange
        var expected = "ABC";
        
        // Act
        var result = StringUtility.convert("aBc");

        // Assert
        assertEquals(expected, result);
    }
*/
    @Test
    public void mustReturnABCWhenInputIsAbcAndModeIsUpperCase() {
        // Arrange
        var expected = "ABC";

        // Act
        var result = StringUtility.convert("AbC", StringUtility.TO_UPPER);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustReturnabcWhenInputIsAbCAndModeIsLowerCase() {
        // Arrange
        var expected = "abc";

        // Act
        var result = StringUtility.convert("AbC", StringUtility.TO_LOWER);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void mustThrowWhenInputContainsNumber() {
        // Assert
        assertThrows(IllegalArgumentException.class, () -> StringUtility.convert("a1bC", StringUtility.TO_LOWER));
    }

    @Test
    public void mustThrowWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> StringUtility.convert(null, StringUtility.TO_UPPER));
    }

    @Test
    public void mustThrowWhenModeIsIncorrect() {
        assertThrows(IllegalArgumentException.class, () -> StringUtility.convert("abc", "toCamelCase"));
    }
}

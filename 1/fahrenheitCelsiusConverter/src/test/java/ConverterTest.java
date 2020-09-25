import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConverterTest
{
    @Test
    public void testFahrenheitFromZeroCelsius()
    {
        // arrange
        float expected = 32F;
        // act
        float actual = Converter.convert(0, Converter.C_TO_F);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFahrenheitFromTenCelcius()
    {
        // arrange
        float expected = 50F;
        // act
        float actual = Converter.convert(10, Converter.C_TO_F);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFahrenheitFromArbitraryCelsius()
    {
        // arrange
        int celsius = 3;
        float expected = 1.8F * celsius + 32;
        // act
        float actual = Converter.convert(celsius, Converter.C_TO_F);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testCelciusFromThirtyTwoFahrenheit()
    {
        // arrange
        int fahrenheit = 32;
        float expected = 0;//fahrenheit-32/1.8F;
        // act
        float actual = Converter.convert(fahrenheit, Converter.F_TO_C);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testCelsiusFromArbitraryFahrenheit()
    {
        // arrange
        int fahrenheit = 5;
        float expected = -15.0F;
        // act
        float actual = Converter.convert(fahrenheit, Converter.F_TO_C);
        // assert
        assertEquals(expected, actual);
    }


}

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConverterTest
{
    /*@Test
    public void testOneYieldsI()
    {
        // arrange
        String expected = "i";
        // act
        String actual = Converter.convert(1);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testTwoYieldsII()
    {
        // arrange
        String expected = "ii";
        // act
        String actual = Converter.convert(2);
        // assert
        assertEquals(expected,actual);
    }

    @Test
    public void testThreeYieldsIII()
    {
        // arrange
        String expected = "iii";
        // act
        String actual = Converter.convert(3);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFourYieldsIV()
    {
        // arrange
        String expected = "iv";
        // act
        String actual = Converter.convert(4);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveYieldsV()
    {
        // arrange
        String expected = "v";
        // act
        String actual = Converter.convert(5);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSixYieldsVI()
    {
        // arrange
        String expected = "vi";
        // act
        String actual = Converter.convert(6);
        // assert
        assertEquals(expected, actual);
    }
*/
    @Test
    public void testOneYieldsI()
    {
        // arrange
        String expected = "i";
        // act
        String actual = Converter.convert(1, 1);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testTenYieldsX()
    {
        // arrange
        String expected = "x";
        // act
        String actual = Converter.convert(1, 10);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testHundredYieldsC()
    {
        // arrange
        String expected = "c";
        // act
        String actual = Converter.convert(1, 100);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testThousandYieldsM()
    {
        // arrange
        String expected = "m";
        // act
        String actual = Converter.convert(1, 1000);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testMaxYieldsMMMCMXCIX()
    {
        // arrange
        String expected = "mmmcmxcix";
        // act
        String actual = Converter.convert(3999);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testUpperLimitExceededYieldsException()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            Converter.convert(4000);
        });
    }

    @Test
    public void testLowerLimitExceededYieldsException()
    {
        assertThrows(IllegalArgumentException.class, () -> { Converter.convert(-5);});
    }


    @Test
    public void testTwoYieldsII()
    {
        // arrange
        String expected = "ii";
        // act
        String actual = Converter.convert(2, 1);
        // assert
        assertEquals(expected,actual);
    }

    @Test
    public void testThreeYieldsIII()
    {
        // arrange
        String expected = "iii";
        // act
        String actual = Converter.convert(3, 1);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFourYieldsIV()
    {
        // arrange
        String expected = "iv";
        // act
        String actual = Converter.convert(4,1);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testFiveYieldsV()
    {
        // arrange
        String expected = "v";
        // act
        String actual = Converter.convert(5, 1);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSixYieldsVI()
    {
        // arrange
        String expected = "vi";
        // act
        String actual = Converter.convert(6,1);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNineYieldsIX()
    {
        // arrange
        String expected = "ix";
        // act
        String actual = Converter.convert(9,1);
        // assert
        assertEquals(expected, actual);
    }


    @Test
    public void testNumberYieldsThousands()
    {
        // arrange
        int expected = 2;
        // act
        int actual = Converter.thousands( 2020);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberYieldsHundreds()
    {
        // arrange
        int expected = 9;
        // act
        int actual = Converter.hundreds(1985);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberYieldsTens()
    {
        // arrange
        int expected = 8;
        // act
        int actual = Converter.tens(1985);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberYieldsOnes()
    {
        // arrange
        int expected = 3;
        // act
        int actual = Converter.ones(1973);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberBelowThousandYieldsNoThousands()
    {
        // arrange
        int expected = 0;
        // act
        int actual = Converter.thousands(765);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberBelowHundredYieldsNoHundreds()
    {
        // arrange
        int expected = 0;
        // act
        int actual = Converter.hundreds(16);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberBelowTenYieldsNoTens()
    {
        // arrange
        int expected = 0;
        // act
        int actual = Converter.tens(5);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testNumberWithNoHundredsYieldsNoHundreds()
    {
        // arrange
        int expected = 0;
        // act
        int actual = Converter.hundreds(2020);
        // assert
        assertEquals(expected, actual);
    }




}

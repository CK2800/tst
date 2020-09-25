import java.util.HashMap;
import java.util.Map;

public class Converter
{
   //static String ones = "iii";
    static Map<Integer, String> romanNumerals = Map.of(
            1,"i",
            5,"v",
            10,"x",
            50,"l",
            100,"c",
            500,"d",
            1000,"m");
    /*public static String convert(int number)
    {
        if (number >= 1 && number < 4)
            return ones.substring(0,number);
        if (number == 4)
            return "iv";
        if (number == 5)
            return "v";
        return "vi";
    }
*/
    public static String convert(int number) throws IllegalArgumentException
    {
        if (number <= 0 || number > 3999)
            throw new IllegalArgumentException("Number must be in the range from 1 to 3999 incl.");
        StringBuilder result = new StringBuilder();
        result.append(convert(thousands(number), 1000));
        result.append(convert(hundreds(number), 100));
        result.append(convert(tens(number), 10));
        result.append(convert(ones(number), 1));
        return result.toString();
    }

    static String convert(int number, int factor)
    {
        StringBuilder result = new StringBuilder();
        // special conditions:
        if (number < 4)
        {
            for(int i = 0; i < number; i++)
            {
                result.append(romanNumerals.get(1*factor));
            }
        }
        else if (number == 4)
        {
            result.append(romanNumerals.get(1*factor));
            result.append(romanNumerals.get(5*factor));
        }
        else if (number == 5)
        {
            result.append(romanNumerals.get(5*factor));
        }
        else if (number == 9)
        {
            result.append(romanNumerals.get(1*factor));
            result.append(romanNumerals.get(10*factor));
        }
        else // number > 5
        {
            result.append(romanNumerals.get(5*factor));
            for(int i = 0; i < number-5; i++)
            {
                result.append(romanNumerals.get(1*factor));
            }
        }

        return result.toString();

    }

    static int thousands(int number)
    {
        return number/1000;
    }

    static int hundreds(int number)
    {
        return (number/100)%10;
    }

    static int tens(int number)
    {
        return (number/10)%10;
    }

    static int ones(int number)
    {
        return number%10;
    }


}

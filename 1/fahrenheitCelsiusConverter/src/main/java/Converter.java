public class Converter
{
    public static final String F_TO_C = "ftoc";
    public static final String C_TO_F = "ctof";
    // c to f: f = c*1.8 + 32.0
    // f to c: c = f - 32.0 / 1.8000
    public static float convert(float degrees, String mode)
    {
        if (F_TO_C.equals(mode))
            return ftoc(degrees);
        else
            return ctof(degrees);

        /*if (celsius == 10)
            return 50;
        return 32;*/

    }

    private static float ctof(float celsius)
    {
        return celsius*1.8F + 32;
        /*if (celsius == 10)
            return 50;
        return 32;*/
    }

    private static float ftoc(float fahrenheit)
    {
        return (fahrenheit-32)/1.8F;
    }
}

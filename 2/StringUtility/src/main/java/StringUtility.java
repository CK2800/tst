public class StringUtility
{
    public static final String TO_UPPER = "uc";
    public static final String TO_LOWER = "lc";

    /*public static String convert(String input)
    {
        return "ABC";
    }*/
    public static String convert(String input, String mode) throws IllegalArgumentException
    {
        //return "ABC";

        // Defensive programming.
        if (input != null)
        {
            if (mode != TO_UPPER && mode != TO_LOWER) throw new IllegalArgumentException("Mode is not supported.");
            if (("").equals(input.trim())) throw new IllegalArgumentException("Empty string cannot be converted.");
            if (!input.matches("[a-zA-ZæøåÆØÅ ]+")) throw new IllegalArgumentException("Some characters in input can not be converted.");
        }
        else
            throw new IllegalArgumentException("Null cannot be converted.");

        return mode == TO_UPPER ? input.toUpperCase() : input.toLowerCase();

    }
}

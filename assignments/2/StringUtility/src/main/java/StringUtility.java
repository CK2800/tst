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
        if (mode != TO_UPPER && mode != TO_LOWER) throw new IllegalArgumentException("Mode is not supported.");
        if (input == null || ("").equals(input.trim())) throw new IllegalArgumentException("Null or empty string cannot be converted.");
        return mode == TO_UPPER ? input.toUpperCase() : input.toLowerCase();
    }
}

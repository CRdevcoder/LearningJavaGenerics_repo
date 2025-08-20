package filehandling.util;

// Thanks to Ethan McCue on Discord for InputParser interface idea. (8/20/25)

public class DataParser {

    // Inner classes that implement InputParser Generic Interface
    // However, they hardcode the type arguements.

    // handles text inputs that contian Integers.
    public static class IntegerInputParser implements InputParser<Integer>
    {
        @Override
        public Integer tryParse(String s) throws Exception {
            return Integer.parseInt(s);
        }
    }

    public static class DoubleInputParser implements InputParser<Double>
    {
        @Override
        public Double tryParse(String s) throws Exception {
            return Double.parseDouble(s);
        }
    }

    // checks if string inputs is only letters, no numbers or symbols allowed
    public static class WordInputParser implements InputParser<String>
    {

        @Override
        public String tryParse(String s) throws Exception {
            boolean hasNumbers = s.matches(".*\\d.*");
            //boolean hasSymbols = false; //s.matches(".*\\w.*");
            if (hasNumbers) {
                throw new Exception("No numbers or symbols allowed! Entered: " + s);
            }
            else
            {
               return s;
            }
        }
        
    }

}

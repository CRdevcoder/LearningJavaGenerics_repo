package filehandling.util;

// Thanks to Ethan McCue on Discord for InputParser interface idea. (8/20/25)

public interface InputParser<T> {

    T tryParse(String s) throws Exception;
}

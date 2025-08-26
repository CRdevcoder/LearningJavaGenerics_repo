package smallprojects.notepad.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.InputMismatchException;

// Backend for notepad project.
// Handle text files by:
//      Writing data from given String to file.
//      reading and returning text file data.

public class TextFileManager {

    private Path filePath; // file path TextFileManager is using

    // MUST be contructed with path path that leads to txt file, which is readable.
    public TextFileManager(Path filePath) throws Exception
    {
        if(!isValidFile(filePath))
        {
            throw new Exception("File path leads to none valid file: " + filePath);
        }

        this.filePath = Paths.get(filePath.toString());
    }

    // Method to test validity of file.
    public static boolean isValidFile(Path p)
    {
        // tests if it exists.
        boolean valid = Files.exists(p) && Files.isRegularFile(p) &&
        !Files.isDirectory(p) && isTextFile(p) && Files.isReadable(p) && Files.isWritable(p);

        return valid;
    }

    // Method to test if path is text file using path extension.
    public static boolean isTextFile(Path p)
    {
        String name = p.getFileName().toString();
        String extension = name.substring(name.indexOf("."));
        return extension.contains("txt");
    }

    // Use Method to save to file.
    // Writes string data to path text file.
    // returns BufferedWriter for you to use.
    public BufferedWriter writeStringToFile( String text, OpenOption ... options ) throws IOException
    {
        
        Charset charset = Charset.forName("US-ASCII");
        BufferedWriter writer = Files.newBufferedWriter(filePath, charset,options);
        writer.write(text);
        return writer;
    }

    // Method to open text file.

    // Method to check if given path leads to text file.

}

package smallprojects.notepad.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.InputMismatchException;

// Backend class for notepad project.
// Use to determine whether a given file is proper text file or not.
// Handle text files by:
//      Writing data from given String to file.
//      reading and returning text file data.
// Dev Note: can transfer these functionalities into generic interface/abstract class.

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
        // tests if it exists, is a text file, etc.
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

    /* writeStringToFile Method: 
    * Writes string data to path text file.
    * returns BufferedWriter for you to use.
    * Utility: Use to save to text file.
    * @param String text - text written to filePath text file.
    * @param OpenOption options - options specifying how the file is opened.
    * @param Charset cs - the charset to used for encoding to file.
    */
    public BufferedWriter writeStringToFile( String text, Charset cs, OpenOption ... options ) throws IOException
    {
        BufferedWriter writer = Files.newBufferedWriter(filePath, cs,options);
        writer.write(text);
        return writer;
    }

    // Method to read text file, return it as String.
    public ArrayList<String> readFiletoStringArrayList(Charset cs) throws IOException
    {
        // Stores lines from file.
        String readLine = null;
        // lines stored in lineList.
        ArrayList<String> lineList = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath, cs);
        // read lines.
        while ((readLine = reader.readLine()) != null) {
            lineList.add(readLine);
        }

        reader.close();

        return lineList;
    }

}

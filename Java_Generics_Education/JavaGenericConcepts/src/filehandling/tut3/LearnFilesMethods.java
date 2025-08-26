package filehandling.tut3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

// Experimenting with Files helper class.
// FOCUS: Files only, no creating/using directories here.
// Using methods that read, write, read, create, and delete files.
// Oracle article (Copying a File or Directory): https://docs.oracle.com/javase/tutorial/essential/io/copy.html
// Oracle article (Reading, Writing, and Creating Files): https://docs.oracle.com/javase/tutorial/essential/io/file.html 

public class LearnFilesMethods {

    public static void main(String[] args) {
        testCreateCopy();
    }

    private static void testCreateCopy()
    {
        Path newTextPath = Paths.get(".", "textFiles","fooFile.txt");
        Path copyTextPath = Paths.get(".","textFiles","copyFooFile.txt");
        try {
            System.out.println("CREATING FILE: " + newTextPath);
            // using createFile method. Will throw error if file already exists.
            // Note: newTextPath file is deleted at end of this program.
            newTextPath = Files.createFile(newTextPath);

            // create new file
            boolean newExists = Files.exists(newTextPath);
            System.out.println("New File Exists?: ");
            existanceTest(newTextPath);
            if (newExists) {
                String newContent = "Hello, I am new!\nI love to party!";
                // write String as bytes into file. 
                Files.write(newTextPath,newContent.getBytes());
                // reads all lines in txt file.
                List<String> list = Files.readAllLines(newTextPath);
                System.out.println("New File's Text: " + list);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace() + "\n" + e.getMessage());
        }

        // Create new text file using BuffredWriter
        try {
            
            System.out.println("\nCREATING FILE WITH BUFFERED WRITER:");
            System.out.println("Buffer File Path:" + copyTextPath);
            
            existanceTest(copyTextPath);
            
            System.out.println("Buffer File is being written to...");
            BufferedWriter bw = Files.newBufferedWriter(copyTextPath,StandardCharsets.US_ASCII,StandardOpenOption.CREATE,StandardOpenOption.WRITE);
            // currently the path doesn't exist. So the BufferedWriter will create a new one.
            bw.write("This is my copy File.");
            // does it now exists?
            existanceTest(copyTextPath);
            // close buffer
            bw.close();
            // reads all lines in txt file.
            List<String> list = Files.readAllLines(copyTextPath, StandardCharsets.US_ASCII);
            System.out.println("Copy File's Text: " + list);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // COPYING and Reading.
        // copy Text File (newTextPath) to different file (copyTextPath) (Using StandardCopyOption enum)
        try {
            System.out.println("\nCOPYING A FILE AND REPLACING TARGET FILE:" + 
            "\nCopying file: " + newTextPath +
            "\nInto file: " + copyTextPath);
            
            System.out.println("Copy File's current content:");
            readFile(copyTextPath);

            Files.copy(newTextPath, copyTextPath, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("Copy File's NEW content:");
            readFile(copyTextPath);
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Delete files
        try {
            Files.deleteIfExists(newTextPath);
            Files.deleteIfExists(copyTextPath);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }

    // HELPER METHODS:
    // tests if a path exists
    public static void existanceTest(Path p)
    {
        boolean fileExists = Files.exists(p);
        System.out.println("File Path: " + p + "\nExists?: " + fileExists);

    }

    // Reads data from file, sends it into Array, then prints it.
    public static void readFile(Path p) throws IOException
    {
        // Note: Encoding and Decoding using provided Charsets.
        BufferedReader r = Files.newBufferedReader(p,StandardCharsets.US_ASCII);

        String line = null;
        // store lines in this array
        ArrayList<String> fileContent = new ArrayList<>();
        while ((line = r.readLine()) != null) {
            fileContent.add(line);
        }

        System.out.println("Content of File: " + p + "\ncontent: " + fileContent);
        // CLOSES READER.
        r.close();
    }

}

package smallprojects.notepad.tests;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.SortedMap;

import filehandling.util.ScannerUtil;
import smallprojects.notepad.backend.TextFileManager;

/* tests backend classes:
*  TextFileManager classes
*  Other:
*  Utilizes Temporary files with Files.createTempFile method.
*/

public class TestBackend {

    public static void main(String[] args) {
        
        System.out.println("TESTING TextFileManenger Class :");
        
        Path testPath = Paths.get(".\\textFiles\\tut2output.txt");

        boolean isTxt = TextFileManager.isTextFile(testPath);
        System.out.println("Is text file: " + isTxt);
        boolean isValidFile = TextFileManager.isValidFile(testPath);
        System.out.println("Is valid file: " + isValidFile);

        // Creating temp txt file, and opening it using TextFileManager
        Path fileFolder = Paths.get(".","textFiles");
        Path tempPath, tempInvalid;

        System.out.println("Creating temp text file in: " + fileFolder);
        
        // using createTempFile
        try {
            tempPath = Files.createTempFile(fileFolder, "testTemp",".txt");
            tempInvalid = Files.createTempFile(fileFolder, "failTemp",".html");
            TextFileManager bestMan = new TextFileManager(tempPath);

            BufferedWriter bestWriter = bestMan.writeStringToFile("This is a temp file\nGood day!", StandardOpenOption.WRITE);
            bestWriter.close();

            // testing constructor exception handling.
            try {
                TextFileManager failMan = new TextFileManager(tempInvalid);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally
            {
                // delete temp file.
                Files.deleteIfExists(tempInvalid);;
            }

            ScannerUtil.askYesNoQuestionInput(new Scanner(System.in),"Enter response to continue program");

            // delete temporary files
            Files.deleteIfExists(tempPath);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Different Charsets
        System.out.println("\nSet of available Charsets:");
        SortedMap<String,Charset> setMap = Charset.availableCharsets();
        System.out.println(setMap.keySet());

        
    }

}

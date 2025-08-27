package smallprojects.notepad.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

import filehandling.util.ScannerUtil;
import smallprojects.notepad.backend.TextFileManager;

/* tests backend classes:
*  TextFileManager class
*  Other:
*  Utilizes Temporary files with Files.createTempFile method.
*/

public class TestBackend {

    public static void main(String[] args) {
        
        System.out.println("TESTING TextFileManenger Class :");
        
        Path testPath = Paths.get(".\\textFiles\\tut2output.txt");

        // Testing isTextFile and isValidFile methods.
        System.out.println("Testing isTextFile and isValidFile methods:\nUsing file:" + testPath);
        boolean isTxt = TextFileManager.isTextFile(testPath);
        System.out.println("Is text file: " + isTxt);
        boolean isValidFile = TextFileManager.isValidFile(testPath);
        System.out.println("Is valid file: " + isValidFile);

        // Creating temp txt file, and opening it using TextFileManager
        Path fileFolder = Paths.get(".","textFiles");
        Path tempPath, tempInvalid;

        System.out.println("\nCreating temp text file in: " + fileFolder);
        
        // using createTempFile
        // Testing TextFileManager constructor and isTextFile method
        try {
            tempPath = Files.createTempFile(fileFolder, "testTemp",".txt");
            tempInvalid = Files.createTempFile(fileFolder, "failTemp",".html");
            TextFileManager bestMan = new TextFileManager(tempPath);


            // testing isTextFile method using Path objects.
            System.out.println("\nTesting isTextFile method with html file directory:");
            System.out.println("Is file:" + tempPath + "\nA text file?: " + TextFileManager.isTextFile(tempPath));
            System.out.println("Is file:" + tempInvalid + "\nA text file?: " + TextFileManager.isTextFile(tempInvalid));


            // testing constructor exception handling.
            System.out.println("\nTesting Contructor Exception handling (when given NON .txt file):");
            try {
                TextFileManager failMan = new TextFileManager(tempInvalid);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally
            {
                // delete temp file.
                Files.deleteIfExists(tempInvalid);
            }

            // pause to observe files being created in IDE file directory.
            //ScannerUtil.askYesNoQuestionInput(new Scanner(System.in),"Enter response to continue program");

            // delete temporary files
            Files.deleteIfExists(tempPath);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Testing Writing to file and reading to file.
        System.out.println("\nTesting Writing to file and Reading file:");

        try {
            tempPath = Files.createTempFile(fileFolder, "sampleTemp",".txt");
            TextFileManager sampleManager = new TextFileManager(tempPath);
            // Writing to file.
            BufferedWriter smWriter = sampleManager.writeStringToFile("This is a sample file\nGood day!",Charset.forName("US-ASCII"), StandardOpenOption.WRITE);
            smWriter.close(); // closing writer.

            // Reading file
            ArrayList<String> textList = sampleManager.readFiletoStringArrayList(Charset.forName("US-ASCII"));
            System.out.println("Contents of file: " + tempPath + "\n" + textList);
            
            // deleting temp file.
            Files.deleteIfExists(tempPath);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        
    }

}

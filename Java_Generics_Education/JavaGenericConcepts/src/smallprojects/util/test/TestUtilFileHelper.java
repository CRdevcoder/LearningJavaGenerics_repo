package smallprojects.util.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import smallprojects.util.UtilFileHelper;

// Tests UtilFileHelper classes methods
public class TestUtilFileHelper {
    
    // runs tests.
    public static void main(String[] args) {
        testArrayToString();
        testFindingFileExtension();
        testReadFile();

        System.out.println("\nEnd of Test.");
    }

    // tests readFiletoStringArrayList method.
    // Uses temporary files.
    private static void testReadFile()
    {
        Path fileFolder = Paths.get(".","textFiles");
        ArrayList<Path> tempPathList = new ArrayList<>();

        System.out.println("Testing Method: readFiletoStringArrayList");
        // Reading existing files.
        try {
            tempPathList.add(Paths.get(fileFolder.toString(),"backingCakeText.txt"));
            tempPathList.add(Paths.get(fileFolder.toString(),"tut2output.txt"));
            System.out.println("Reading Files from Path ArrayList:\n" + tempPathList + "\n");

            System.out.println("Reading files:");
            for (Path p : tempPathList) {
                ArrayList<String> content = UtilFileHelper.readFiletoStringArrayList(p,StandardCharsets.UTF_8);
                System.out.println("Red content: " + content + "\nFile Dir: " + p);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e.getClass());
        }

        // Testing non Existant file exception:
        System.out.println("\nTesting non Existant file Exception:");
        try {
            // create fake path, then use it.
            Path fakePath = Paths.get(".", "foo","fakeFile.csv");
            System.out.println("Non existent file path: " + fakePath);
            UtilFileHelper.readFiletoStringArrayList(fakePath,StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println( "Exception message:\n" + e.getMessage());
        }
    }

    // tests getPathFileExtension and checkFileExtension method.
    private static void testFindingFileExtension()
    {
        System.out.println("Testing Method: getPathFileExtension");
        // path to textFile folder.
        Path fileFolder = Paths.get(".","textFiles");
        // example paths (don't lead to read files).
        ArrayList<Path> examplePaths = new ArrayList<>();
        examplePaths.add(Paths.get(".", "textFiles","fake.txt"));
        examplePaths.add(Paths.get(".", "nonFile","fake_file.html"));
        examplePaths.add( Paths.get(".", "docs","tables","fake.csv"));
        examplePaths.add( Paths.get(".", "docs","tables","fake.phonyextension"));

        System.out.println("ArrayList examplePaths:\t" + examplePaths);

        // use paths to test method:
        try {
            for (Path p : examplePaths) {
                System.out.println("Extension Found:\t" + UtilFileHelper.getPathFileExtension(p));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // TESTING EXCEPTION
        System.out.println("\nTesting Method Exception - No file extension found in path.\nPath used:" + fileFolder);

        // use fileFolder path, a path directory that doesn't lead to a file.
        try {
            UtilFileHelper.getPathFileExtension(fileFolder);
        } catch (Exception e) {
            System.out.println("Exception message:\n" + e.getMessage() + "\n");
        }

        // Testing checkFileExtension using Path ArrayList.
        System.out.println("Testing Method: checkFileExtension");

        try {
            // looking for txt extension
            System.out.println("Searching for extension: txt\nIn Paths of ArrayList: "+examplePaths);
            for (Path p : examplePaths) {
                System.out.println("Extension txt Found:\t" + UtilFileHelper.checkFileExtension(p,"txt") 
                + "\t" + p);
            }
            // looking for html extension
            System.out.println("\nSearching for extension: html");
            for (Path p : examplePaths) {
                System.out.println("Extension txt Found:\t" + UtilFileHelper.checkFileExtension(p,"html")
                + "\t" + p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();


    }

    // tests convertListToDelimitatedString method.
    private static void testArrayToString()
    {
        System.out.println("Testing Method: convertListToDelimitatedString");
        // ArrayList, could be csv row, or lines from txt file.
        ArrayList<String> fruitList = new ArrayList(Arrays.asList("Apple", "Banana","Pie", "Orange","Coconut"));
        ArrayList<String> emptyList = new ArrayList<>();

        // Deliminators:
        ArrayList<String> deliminatorsArray = new ArrayList(Arrays.asList(",","\t",".","\\","\s"));

        System.out.println("Taking Arraylist: " + fruitList + "\nConverting into String with deliminators: " + deliminatorsArray);
        try
        {
            for (int i = 0; i < deliminatorsArray.size(); i++) {
                String fLine = UtilFileHelper.convertListToDelimitatedString(fruitList ,deliminatorsArray.get(i) );
                System.out.println("Output: " + fLine);
            }
        }
        catch(IOException e)
        {   
            System.out.println(e.getMessage());
        }

        System.out.println("Testing Empty ArrayList Exception:\n" + "ArrayList: " + emptyList);

        try {
            UtilFileHelper.convertListToDelimitatedString(emptyList,"+");
        } catch (Exception e) {
            System.out.println("Exception Message:\n" + e.getMessage() + "\n");
        }
        
    }

}

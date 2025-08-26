package filehandling.tut3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

// Experiementing with Path Class and Files Helper Class
// Oracle Documentation: https://docs.oracle.com/javase/tutorial/essential/io/fileAttr.html
// Java NIO Path: https://jenkov.com/tutorials/java-nio/path.html

public class LearnPath {

    public static void main(String[] args) {
        
        Path p1 = Paths.get(".","csvFiles","exampleData.csv");
        Path folder1 = Paths.get(".","csvFiles");

        // Use .\\ for relative directory. Project starts at "Java_Generics_Education" file.
        Path currDir = Paths.get(".\\Java_Generics_Education\\JavaGenericConcepts\\csvFiles\\exampleData.csv");
        System.out.println("Curr Dir:\n"+currDir.toAbsolutePath());
        System.out.println("currDir: "+Files.exists(currDir));
        System.out.println( "p1 Path:\n"+ p1.toAbsolutePath());
        System.out.println("p1 Exists:" + Files.exists(p1));

        System.out.println("\nTest this is folder: " + folder1);
        System.out.println("Is Directory: " + Files.isDirectory(folder1));
        System.out.println("Is File: " + Files.isRegularFile(folder1) + "\n");
        
        // Note: opening the project in different folders changes the output of toAbsolutePath().

        if (Files.exists(p1)) {
            System.out.println( "Is Directory (Folder)?: " + Files.isDirectory(p1) + 
            "\nExecutable:" + Files.isExecutable(p1) + 
            "\nReadable: " + Files.isReadable(p1) +
            "\nFile Name: " + p1.getFileName() +
            "\nGet Name: " + p1.getName(1) +
            "\nFile System: " + p1.getFileSystem() + 
            "\nPrincipalLookUp Service: " + p1.getFileSystem().getUserPrincipalLookupService() + "\n");

            // EXPERIEMENTING with BasicFileAttributeView.

            // BasicFileAttributeView used to get all the file data for the attributes.
            // Use to read meta data from file, then store into BasicFileAttributes object.
            BasicFileAttributeView view= Files.getFileAttributeView( p1, BasicFileAttributeView.class);

            try {
                // method to read the file attributes.
                BasicFileAttributes attribute = view.readAttributes(); 
                
                System.out.println( "Last Modified Time : " + attribute.lastModifiedTime() +
                "\nCreation Time : "  + attribute.creationTime());
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println();

        }
        
    }

}

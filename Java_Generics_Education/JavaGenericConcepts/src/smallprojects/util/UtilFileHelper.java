package smallprojects.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

// utility class that provides general purpose static methods for reading and writing files.

public class UtilFileHelper {

    // Method to read text file, return it as String ArrayList.
    // @param Path filePath - file being read by Buffered Reader.
    // @param Charset cs - the charset used for decoding. 
    // untested.
    public static ArrayList<String> readFiletoStringArrayList(Path filePath,Charset cs) throws IOException, FileNotFoundException
    {
        if (Files.exists(filePath) == false) {
            throw new FileNotFoundException("(readFiletoStringArrayList) File can't be read, it doesn't exist:" + filePath);
        }

        // Stores lines from file.
        String readLine = null;
        // lines stored in lineList.
        ArrayList<String> lineList = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(filePath, cs);
        // read lines.
        while ((readLine = reader.readLine()) != null) {
            lineList.add(readLine);
        }
        // close reader
        reader.close();

        return lineList;
    }

    // takes String arrayList, combines them into singular string.
    // Each element is seperated by specified deliminator.
    // untested.
    public static String convertListToDelimitatedString(ArrayList<String> contentList, String deliminator) throws IOException
    {
        String combinedContent = "";

        if(contentList.isEmpty())
        {
            throw new IOException("(convertListToDelimitatedString) Given String ArrayList contentList is empty: " + contentList);
        }
        else if(contentList.size() > 1){
            for (int i = 0; i < contentList.size() - 1; i++) {
                combinedContent += (contentList.get(i) + deliminator);
            }

            combinedContent += contentList.get(contentList.size()-1);
        }
        else
        {
            combinedContent += contentList.get(0);
        }
        return combinedContent;
    }

    // Determine the file extension of a file path.
    // Returns file extension without the dot as String.
    // untested.
    public static String getPathFileExtension(Path p) throws IOException
    {

        String name = p.getFileName().toString();
        int dotIndex = name.indexOf("."); // returns -1 if no "." is found.
        // if no period for a possible extension is found, that means it's not a file.
        // Thus, an throw exception.
        if (dotIndex <= -1) {
            throw new IOException("(getPathFileExtension) Path directory has NO file extension: " + p);
        }
        // use dot to get file extension. EX: txt, html, docx, etc.
        String extension = name.substring(dotIndex+1);
        return extension;
    }

    // check if a directory leads to a file with a specific file extension.
    // returns true if targetExtension is found in Path's extension.
    // untested.
    public static boolean checkFileExtension(Path p, String targetExtension) throws IOException
    {
        String pathExtension = getPathFileExtension(p);
        return pathExtension.contains(targetExtension);
    } 
    
}

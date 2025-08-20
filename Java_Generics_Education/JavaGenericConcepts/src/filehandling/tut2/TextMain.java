package filehandling.tut2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

// Writing on text files in java tutorial
// Video: Java File Input/Output - It's Way Easier Than You Think
// Channel: Coding with John
// Link: https://www.youtube.com/watch?v=ScUJx4aWRi0

// Note: Didn't copy it one-to-one, added my own features
// You can text input into the console, and it will be copied into the text file.

public class TextMain {

    public static void main(String[] args) {
        
        BufferedWriter  writer = null;
        BufferedReader reader = null;

        String filePath = "textFiles//tut2output.txt";

        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("Writing to a file:");

            // get user input
            ArrayList<String> userText = inputText();

            // enter user input into text file
            for(String line : userText)
            {
                writer.write("\n" + line);
            }


            // close writer
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        // print text file results into console.
        try {
            reader = new BufferedReader(new FileReader(filePath));
            System.out.println(reader.readLine());

            // read lines from text file, then print them.
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        System.out.println();
    } // Main End.

    // enter lines of text into console, it is stored into ArrayList, then returned.
    public static ArrayList<String> inputText()
    {
        System.out.println("Enter text into console: \n");

        Scanner userInput = new Scanner(System.in);

        ArrayList<String> textLines = new ArrayList<>();

        // user will be able to enter text as long as this is true.
        boolean typing = true;

        do {

            String line = userInput.nextLine();
            textLines.add(line);

            String answer = "";
            //repeats question until valid answer is given
            boolean invalidAnswer = true;
            do{
                System.out.print("\nContinue typing? (y/n): ");
                answer = userInput.next();

                if (answer.equals("y")) {
                    invalidAnswer = false;
                }
                else if(answer.equals("n"))
                {
                    invalidAnswer = false;
                }

                
            }while(invalidAnswer); //end of asking question.
            // folly: also did - && answer.equals("") - that caused glitches.

            // answer should be y or n.
            if(answer.equals("n"))
            {
                typing = false;
            }
            else if(answer.equals("y")){
                typing = true;
            }
            else
            {
                System.out.println("ERROR");
                System.exit(0);
            }

            System.out.println();
            userInput.nextLine();
            
        } while (typing);


        return textLines; 
    }

}

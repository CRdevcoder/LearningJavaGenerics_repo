package filehandling.util;

import java.io.IOException;
import java.util.Scanner;

public class ScannerUtil {

    // pass Scanner object into method.
    // ask user yes or no question entered in String parameter.
    // User enters y or n, or else it repeats the question.
    // If a non y or n question passed through, it thows an error.

    public static boolean askYesNoQuestionInput( Scanner userInput, String question) throws IOException
    {
        boolean userResponse = false;
        String answer = "";
    
        // repeat asking until valid answer is submitted.
        boolean invalidAnswer = true;
        do{
            System.out.print(question + " (y/n): ");
            answer = userInput.next();

            if (answer.equals("y")) {
                    invalidAnswer = false;
                }
                else if(answer.equals("n"))
                {
                    invalidAnswer = false;
                }

                
            }while(invalidAnswer); //end of asking question.

            // remove all whitespace from answer
            answer = answer.replaceAll("\\s", "");

            if(answer.equals("n"))
            {
                userResponse = false;
            }
            else if(answer.equals("y")){
                userResponse = true;
            }
            else
            {
                // throws exception if given incorrect input. 
                throw new IOException("Did not enter (y) or (n) as answer, entered: " + answer);
            }
        
            return userResponse;
    }

    // UNFINISHED METHOD:
    // Ask for Number or String text Input.
    // Receives user input, then tests if it is the datatype it asked for.
    // Returns that datatype if user enters correct datatype.
    // Or REJECTS user's input and asks question again.
    // Options: Numbers - Integer, Double, Float, or just String.
    public static <T> T askForEnterData(Scanner userInput, String message)
    {
        String rawInput = null;
        T dataInput = null;

        // repeat asking until valid answer is submitted.
        boolean invalidAnswer = true;
        do{
            System.out.println("Enter " + message + " : ");
            rawInput = userInput.nextLine();

            // PROBLEM: How to test if user entered proper String or Number input?
                
            }while(invalidAnswer); //end of asking question.

        return dataInput;
    }

}

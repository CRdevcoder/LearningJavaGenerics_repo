package filehandling.tut1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import filehandling.util.DataParser;
import filehandling.util.ScannerUtil;

// Summary:
// Explores reading and printing csv file data.

// tutorial:
// https://www.youtube.com/watch?v=zKDmzKaAQro
// not exact copy, I took my own liberties
public class CsvMain {

    public static void main(String[] args) {
        
        // Handle CSV files.
        String file = "csvFiles\\exampleData.csv";
        BufferedReader reader = null;
        String line = "";

        // stores rows of csv file
        ArrayList<String[]> tableRows = new ArrayList<>();

        // try opening file
        try{
            reader = new BufferedReader(new FileReader(file));
            // read lines in file, stop when next line is null.
            while((line = reader.readLine()) != null)
            {
                String[] row = line.split(",");
                // add to arraylist
                tableRows.add(row);
            }

        }
        catch( Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            // close reader
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // print out data
        for (String[] row : tableRows) {
            
            for (String index : row) {
                System.out.printf("%-14s",index);
            }
            System.out.println();
        }

        // USER INPUT CODE:
        // Ask user if they want to enter a new row.
        Scanner inputScan = new Scanner(System.in);
        boolean appendNewRow = false;
        String[] row = new String[2]; // stores user inputs.
        Double money = null;

        try {
            appendNewRow = ScannerUtil.askYesNoQuestionInput(inputScan, "Do you want to add a new row?");
        } catch (Exception e) {
            e.getMessage();
        }

        if (appendNewRow) {
            System.out.println("Lets do this!");
            // ADD METHOD THAT TAKES SCANNER AND ASKS FOR USER DATA.
            // First asks for String, for name.
            // Secound, asks for String, for salary
            // Finally, asks for Double, for pay amount.

            row[0] = ScannerUtil.askForEnterData(inputScan,"Employee name",new DataParser.WordInputParser());
            System.out.println("You entered: " + row[0]);

            row[1] = ScannerUtil.askForEnterData(inputScan, "Payment type (Salary or Wage)", new DataParser.WordInputParser());
            System.out.println("You entered: " + row[1]);

            money = ScannerUtil.askForEnterData(inputScan, "Pay Amount (Cash)", new DataParser.DoubleInputParser());
            System.out.println("You entered: " + money);

        }
        else
        {
            System.out.println("Goodbye...");
            System.exit(0);
        }

    }

    
}

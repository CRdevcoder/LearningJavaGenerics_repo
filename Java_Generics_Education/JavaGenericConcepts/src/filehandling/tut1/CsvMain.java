package filehandling.tut1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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


    }

}

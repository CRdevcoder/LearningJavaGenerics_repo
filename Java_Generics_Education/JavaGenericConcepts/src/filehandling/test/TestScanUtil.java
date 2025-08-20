package filehandling.test;

import java.util.ArrayList;
import java.util.Scanner;

import filehandling.util.DataParser;
import filehandling.util.InputParser;
import filehandling.util.ScannerUtil;
import generics.basics.tutorial1.OrderedPair;

// testing InputParser Interface and DataParser
// v1 created: (8/20/25)
public class TestScanUtil {

    public static void main(String[] args) {
        
        ArrayList<String> wordInputList = new ArrayList<>();

        // All askForEnterData calls will be using same WordInputParser.
        DataParser.WordInputParser wordTester = new DataParser.WordInputParser();
        // number questions will use same integer and double input parsers.
        DataParser.IntegerInputParser intTester = new DataParser.IntegerInputParser();
        DataParser.DoubleInputParser doubleTester = new DataParser.DoubleInputParser();

        // WORD INPUT BASED QUESTIONS:
        // Stores message and classes that implemented InputParser<String>.
        ArrayList<OrderedPair<String, InputParser<String>>> questionList = new ArrayList<>();
        questionList.add(new OrderedPair<String,InputParser<String>>("your first name", wordTester));
        questionList.add(new OrderedPair<String,InputParser<String>>("your last name", wordTester));
        questionList.add(new OrderedPair<String,InputParser<String>>("your favorite animal name", wordTester));

        // Scanner reads through this for input.
        String autoInput = "90 57 Daisy yu8 3lf Robswerth 67e tr6 Cheeta";

        // INTEGER INPUT BASED QUESTIONS:
        ArrayList<OrderedPair<String, InputParser<Integer>>> integerQueryList = new ArrayList<>();
        // Integer questions
        integerQueryList.add(new OrderedPair<String,InputParser<Integer>>("number of pets", intTester));
        integerQueryList.add(new OrderedPair<String,InputParser<Integer>>("number of eggs", intTester));

        // DOUBLE INPUT BASED QUESTIONS:
        ArrayList<OrderedPair<String, InputParser<Double>>> doubleQueryList = new ArrayList<>();
        // Double Questions
        doubleQueryList.add(new OrderedPair<String,InputParser<Double>>("how much MOOLAH (cash)", doubleTester));
        doubleQueryList.add(new OrderedPair<String,InputParser<Double>>("the height in cm", doubleTester));

        
        // ask yes or no question
        // if no, go through each question and ask user for input.
        // if yes, have scanner read through autoInput String for question answers.

        boolean enterAutoPlayInputs = false;
        try {
            enterAutoPlayInputs = ScannerUtil.askYesNoQuestionInput(new Scanner(System.in),"Do you want to auto-play inputs");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println(enterAutoPlayInputs);

        if(enterAutoPlayInputs == false)
        { // USER INPUT
            for (OrderedPair<String,InputParser<String>> pair : questionList) 
            {
            // create new scanner every loop. Fixes bug.
            Scanner keyboard = new Scanner(System.in);
            // ask user until proper answer is received.
            String wordInput = ScannerUtil.askForEnterData(keyboard,pair.getKey(),pair.getValue());
            wordInputList.add(wordInput);
            }
        }
        else { // AUTO PLAY INPUT
            Scanner textRead = new Scanner(autoInput);
            int index = 0;
            while(textRead.hasNext() && (index < questionList.size()))
            {
                // pair used to define questions.
                OrderedPair<String,InputParser<String>> pair = questionList.get(index);
                // ask for input from Scanner.
                String autoAnswer = ScannerUtil.askForEnterData(textRead,pair.getKey(),pair.getValue());
                System.out.println("Valid Auto Answer: " + autoAnswer);
                wordInputList.add(autoAnswer);
                index++; // continue questions.
            }
        }
        
        System.out.println("\nList of valid words entered:");
        for (String w : wordInputList) {
            System.out.println(w);
        }

        //auto play number questions.
        String intAnswers = "3t 7u 3 8* 9/7"; // integer inputs.
        ArrayList<Integer> intAnsInputList = new ArrayList<>();
        TestScanUtil.<Integer>autoReadThroughPairList(integerQueryList, intAnsInputList,intAnswers);
        
        System.out.println("\nList of valid Integers entered:");
        for (Integer w : intAnsInputList) {
            System.out.println(w);
        }
        System.out.println();

        String doubleAnswers = "() 7.- 3-4 12 cat w@ 78.98 vt"; // double inputs.
        ArrayList<Double> doubleAnsInputList = new ArrayList<>();
        TestScanUtil.<Double>autoReadThroughPairList(doubleQueryList, doubleAnsInputList,doubleAnswers);

        System.out.println("\nList of valid Doubles entered:");
        for (Double w : doubleAnsInputList) {
            System.out.println(w);
        }
        System.out.println("\nEnd Of Test");


    }

    public static <T> void autoReadThroughPairList(ArrayList<OrderedPair<String, InputParser<T>>> qList, ArrayList<T> answerList, String autoData)
    {
        // auto play number questions
            Scanner numberRead = new Scanner(autoData);
            int index = 0; // stop if qList size is surpassed (all questions satisfied)
            while(numberRead.hasNext() && (index < qList.size()))
            {
                // pair used to define questions.
                OrderedPair<String,InputParser<T>> pair = qList.get(index);
                // Scanner reads through string.
                // if string runs out without getting valid answer it will through error.
                try {
                    T autoAnswer = ScannerUtil.askForEnterData(numberRead,pair.getKey(),pair.getValue());
                    System.out.println("Valid Auto Answer: " + autoAnswer);
                    answerList.add(autoAnswer);
                } catch (Exception e) {
                    System.out.println( "Exception: " + e.getMessage());
                    break;
                }

                index++; // continue to next question when finally receive valid answer.
            }
        
    }
    

}

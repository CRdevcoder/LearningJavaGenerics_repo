package generics.basics.tutorial2.wildcards;

import java.util.ArrayList;
import java.util.Arrays;

// tests WildCardFunctions class methods.

public class WildCardTest {

    public static void main(String[] args) {
      
        // Lower Bound method:
        ArrayList<Integer> numArray = new ArrayList<>(Arrays.asList(23,45,57,82,12));
        WildCardFunctions.addIntegers(numArray);

        ArrayList<Double> doubleArray = new ArrayList<>(Arrays.asList(1.2,0.1,0.5,0.9,0.023));
        // Using Upper Bound method: can use both Integer and Double Arraylists!
        System.out.println(WildCardFunctions.sumNumbersToDouble(doubleArray));
        System.out.println(WildCardFunctions.sumNumbersToDouble(numArray));

        //Unbound Wildcard:
        WildCardFunctions.printList(numArray);

        // Non-Wildcard method:
        WildCardFunctions.displayToString(numArray);

    }


}

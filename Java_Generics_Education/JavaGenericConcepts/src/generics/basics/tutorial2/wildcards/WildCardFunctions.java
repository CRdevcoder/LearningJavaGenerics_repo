package generics.basics.tutorial2.wildcards;

import java.util.ArrayList;

public class WildCardFunctions {
    // prints objects.
    public static <T> void displayToString(T typeData)
    {
        System.out.println(typeData.toString());
    }

    // Lower Bound Wildcard - uses super keyword
    // Only accepts Arraylists that contain Integers, or super classes of Interger (Object and Number)
    public static void addIntegers( ArrayList <? super Integer> list)
    {
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
    }

    // Upper Bound Wildcard 
    // accepts any Arraylist that stores objects which extend Number class.
    public static double sumNumbersToDouble( ArrayList<? extends Number> list)
    {
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    // Unbound Wildcard - no extends or super used
    public static void printList(ArrayList<?> list)
    {

        for( Object element : list )
        {
            System.out.print(element + ", ");
        }
        System.out.println();
    }


    // Upper Bound Wildcard
    // ???
}

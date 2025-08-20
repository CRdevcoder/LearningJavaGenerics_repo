package generics.basics.tutorial1;

import java.util.ArrayList;

// Tutorial demonstrating how to create Generic classes, interfaces, and methods.
public class GenericApp {

    public static void main(String[] args) {
        
        //Creating Boxes, Container that stores one value.
        Box<Integer> myNumber = new Box<>(); // Box with Type Arguement: Integer
        myNumber.setTypeData(25); // stores an Integer.
        // This Box uses type arguement String
        Box<String> myFavoriteAnimal = new Box<>();
        myFavoriteAnimal.setTypeData("Giraffe");

        // You can't use a non-Integer, like String, for the myNumber Box.
        // Causes compilation error, not runtime error.
        /*try {
            myNumber.setTypeData("word");;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
            */


        // print out Box data.
        Util.displayToString(myNumber);
        Util.displayToString(myFavoriteAnimal);
        // create Arraylist of Boxes.
        ArrayList<Box<String>> boxZoo = new ArrayList<>();

        boxZoo.add(myFavoriteAnimal);
        boxZoo.add(myFavoriteAnimal);

        Util.displayToString(boxZoo);

        // Creating containers that store a key and value.
        OrderedPair<Integer,String> player1 = new OrderedPair<Integer,String>(24, "Cleavland");
        OrderedPair<String,String> america = new OrderedPair<String,String>("USA", "United States");
        OrderedPair<String,String> germany = new OrderedPair<String,String>("GER", "Germany");

        // ArrayList of ordered pairs:
        ArrayList<OrderedPair<String,String>> countryList = new ArrayList<>();
        countryList.add(america);
        countryList.add(germany);
        // prints out arrayList of OrderedPair objects.
        Util.displayToString(countryList);

        // Testing NamePair class.
        NamePair freindName = new NamePair("Phillip", "Fry");
        System.out.println( freindName.getKey()+ " " + freindName.getValue());

    }

}

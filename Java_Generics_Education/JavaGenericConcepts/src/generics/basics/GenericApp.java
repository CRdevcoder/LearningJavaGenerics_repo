package generics.basics;

import java.util.ArrayList;

public class GenericApp {

    public static void main(String[] args) {
        
        Box<Integer> myNumber = new Box<>();
        myNumber.setTypeData(25);

        Box<String> myFavoriteAnimal = new Box<>();
        myFavoriteAnimal.setTypeData("Giraffe");

        // print out Box data.
        Util.displayToString(myNumber);
        Util.displayToString(myFavoriteAnimal);

        ArrayList<Box<String>> boxZoo = new ArrayList<>();

        boxZoo.add(myFavoriteAnimal);
        boxZoo.add(myFavoriteAnimal);

        Util.displayToString(boxZoo);

    }

}

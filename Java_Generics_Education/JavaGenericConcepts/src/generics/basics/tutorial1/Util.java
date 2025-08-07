package generics.basics.tutorial1;

public class Util {
    // calls toString() method of received typeData and prints it.
    public static <T> void displayToString(T typeData)
    {
        System.out.println(typeData.toString());
    }
}

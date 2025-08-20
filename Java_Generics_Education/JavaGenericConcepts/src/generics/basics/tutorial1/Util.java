package generics.basics.tutorial1;

// contains static methods.
public class Util {
    // Polymorphism applied here. Every class inherits toString method from Object class.
    // calls toString() method of received typeData and prints it.
    public static <T> void displayToString(T typeData)
    {
        System.out.println(typeData.toString());
    }
}

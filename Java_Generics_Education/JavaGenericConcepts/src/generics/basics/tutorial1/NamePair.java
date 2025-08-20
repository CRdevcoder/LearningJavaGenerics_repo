package generics.basics.tutorial1;

// Can enter arguements into type parameters of implemented interface
public class NamePair implements Pair<String,String>{

    String firstName;
    String lastName;

    public NamePair(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getKey() {
        return firstName;
    }

    @Override
    public String getValue() {
        return lastName;
    }

}

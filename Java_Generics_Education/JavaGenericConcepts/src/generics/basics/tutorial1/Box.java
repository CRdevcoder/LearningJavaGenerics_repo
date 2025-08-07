package generics.basics.tutorial1;

public class Box<T> {

    // All T's will become same data type as the one type arguement given to a Box Object on creation.
    private T typeData;

    public void setTypeData(T typeData) {
        this.typeData = typeData;
    }

    public T getTypeData() {
        return typeData;
    }

    @Override
    public String toString() {
        return "Box [" + typeData + "]";
    }

}

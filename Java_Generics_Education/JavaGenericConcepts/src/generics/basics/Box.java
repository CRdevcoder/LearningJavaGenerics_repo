package generics.basics;

public class Box<T> {

    // Will be same type as param type arguement.
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

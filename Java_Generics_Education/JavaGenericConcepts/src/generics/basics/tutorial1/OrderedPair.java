package generics.basics.tutorial1;

// Class summary: Data Structure that stores a key of type K and value of type V
// Use to organize two associated values together.
public class OrderedPair<K,V> implements Pair<K,V> {

    private K key;
    private V value;

    public OrderedPair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "OrderedPair{" +
        "Key=" + key +
        ", Value=" + value +
        '}';
    }

}

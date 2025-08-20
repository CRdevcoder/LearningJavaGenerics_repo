package generics.basics.tutorial1;

// Summary: Implement for classes that store 2 pairs of data types K and V.
// Generic Interface.
public interface Pair<K,V> {
    // Key (K) with corresponding Value (V)
    public K getKey();
    public V getValue();
}

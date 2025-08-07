package generics.basics.tutorial1;

// Generic Interface.
public interface Pair<K,V> {
    // Key (K) with corresponding Value (V)
    public K getKey();
    public V getValue();
}

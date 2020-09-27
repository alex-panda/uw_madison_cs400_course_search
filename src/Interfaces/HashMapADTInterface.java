package Interfaces;

import java.util.NoSuchElementException;

/**
 * A public interface to implement to make a HashMap
 * */
public interface HashMapADTInterface<KeyType,ValueType> {
    public boolean put(KeyType key, ValueType value);
    public ValueType get(KeyType key) throws NoSuchElementException;
    public int size();
    public boolean containsKey(KeyType key);
    public ValueType remove(KeyType key);
    public void clear();
}

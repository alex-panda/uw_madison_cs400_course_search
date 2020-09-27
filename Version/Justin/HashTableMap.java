// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: There are 4 source files in this project: MapADT.java, KeyValuePair.java,
// HashTableMap.java, TestHashTable.java.

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements a Hash Table structure which supports storing key-value pairs in hash table
 * maps, adding into, getting, removing and lookup pairs from the table. Instance of this class can
 * process rehashing automatically when the load factor is greater than or equals to 80%.
 *
 * @author Justin Qiao
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private int capacity; // the current capacity of the hashTable
  private LinkedList<KeyValuePair<KeyType, ValueType>>[] hashTable; // the hushTable instance
  private int size; // the number of key-value pairs inside the hashTable

  /**
   * Creates an empty HashTableMap by a given capacity. Initializes private fields.
   * 
   * @param capacity - a int value of the capacity of the hashTable
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    hashTable = new LinkedList[capacity];
    size = 0;
    for (int i = 0; i < capacity; i++)
      hashTable[i] = new LinkedList<KeyValuePair<KeyType, ValueType>>();
  }

  /**
   * Creates an empty HashTableMap with default capacity(10).
   * 
   */
  public HashTableMap() {
    this(10);
  }

  /**
   * Add a new key-value pair into the hashTable. Nothing will be changed if the key already exists
   * in the hashTable. Doubling the hashTable and rehashing the data when loadFactor is greater than
   * or equals to 80%.
   * 
   * @param KeyType   input key
   * @param ValueType value value that is matched to the key
   * @see MapADT#put
   * @return true if successfully added the new data pair, and false otherwise
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    // check whether the key already exists in the hashTable
    KeyValuePair<KeyType, ValueType> data = new KeyValuePair<KeyType, ValueType>(key, value);
    int hashIndex = Math.abs(key.hashCode()) % capacity;
    if (hashTable[hashIndex].size() != 0)
      for (int i = 0; i < hashTable[hashIndex].size(); i++)
        if (data.getKey().equals(hashTable[hashIndex].get(i).getKey()))
          return false;
    // check whether rehashing is needed to add the new data pair
    double loadFactor = (double) (size + 1) / capacity;
    if (loadFactor >= 0.8)
      rehashing();
    // add the new data pair if the key is valid
    hashTable[hashIndex].add(data);
    size++;
    return true;
  }

  /**
   * Lookup a key-value pair in the hashTable by the given key. Throw exception if no data found.
   * 
   * @param KeyType input key
   * @return the ValueType value corresponding to the key if matched
   * @see MapADT#get
   * @throws NoSuchElementException if no data found
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // return the value matched to the key if it exists in the hashTable
    int hashIndex = Math.abs(key.hashCode()) % capacity;
    if (hashTable[hashIndex].size() != 0)
      for (int i = 0; i < hashTable[hashIndex].size(); i++)
        if (hashTable[hashIndex].get(i).getKey().equals(key))
          return hashTable[hashIndex].get(i).getValue();
    // throw exception if the key does not exist in the hashTable
    throw new NoSuchElementException();
  }

  /**
   * Return the number of elements inside the current hashTable.
   * 
   * @return the number of element inside the current hashTable
   * @see MapADT#size
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Checks whether the given key exists in the current hashTable.
   * 
   * @return true if key exists, false otherwise
   * @see MapADT#containsKey
   */
  @Override
  public boolean containsKey(KeyType key) {
    try {
      get(key);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Remove and return a key-value pair in the hashTable by the given key. Return null and change
   * nothing if no key matched.
   * 
   * @param KeyType input key
   * @return the ValueType value corresponding to the key if removed, null if no key matched
   * @see MapADT#remove
   */
  @Override
  public ValueType remove(KeyType key) {
    // remove and return the value matched to the given key, if there is any
    if (containsKey(key)) {
      int hashIndex = Math.abs(key.hashCode()) % capacity;
      for (int i = 0; i < hashTable[hashIndex].size(); i++)
        if (hashTable[hashIndex].get(i).getKey().equals(key)) {
          size--;
          return hashTable[hashIndex].remove(i).getValue();
        }
    }
    // return null otherwise
    return null;
  }

  /**
   * Remove all data-value pairs in the hashTable.
   * 
   * @see MapADT#clear
   */
  @Override
  public void clear() {
    for (int i = 0; i < capacity; i++)
      if (hashTable[i].size() != 0)
        hashTable[i].clear();
    size = 0;
  }

  /**
   * Doubles the table size and rehash the data already in the table.
   * 
   */
  private void rehashing() {
    // create and initialize the new table of doubled capacity
    LinkedList<KeyValuePair<KeyType, ValueType>>[] newTable = new LinkedList[capacity * 2];
    for (int i = 0; i < capacity * 2; i++)
      newTable[i] = new LinkedList<KeyValuePair<KeyType, ValueType>>();
    // rehashing
    for (int i = 0; i < capacity; i++)
      for (int j = 0; j < hashTable[i].size(); j++) {
        KeyValuePair<KeyType, ValueType> data = hashTable[i].get(j);
        int newIndex = Math.abs(data.getKey().hashCode()) % (2 * capacity);
        newTable[newIndex].add(data);
      }
    // use the new table to replace the original one
    capacity *= 2;
    hashTable = newTable;
  }

  /**
   * This method is just for testing purpose to check whether the table have correct capacity.
   * 
   * @return the capacity of the current hashTable
   */
  protected int getCapacity() {
    return capacity;
  }

}

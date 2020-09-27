// --== CS400 File Header Information ==--
// Name: Allistair Nathan Mascarenhas
// Email: anmascarenha@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * This class implements the MapADT interface to create a hash table that is used to store key and
 * value pairs. The class also defines methods that lets us interact with it by adding and removing
 * data, etc.
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  private LinkedList<KeyValuePair<KeyType, ValueType>>[] hashTable;
  private int size;
  private int capacity;

  /**
   * Constructor initializes the array of linked lists by using the argument capacity and creates an
   * array of that size. It also initializes each index of the array by creating a linked list and
   * sets size of the hash table to 0.
   * 
   * @param capacity - How big the array of linked lists should be
   */
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    hashTable = new LinkedList[this.capacity];
    for (int i = 0; i < hashTable.length; i++) {
      hashTable[i] = new LinkedList();
    }
    size = 0;
  }

  /**
   * This no argument constructor makes a call to the other constructor in this class and passes 10
   * as the default capacity of the array.
   */
  public HashTableMap() {
    this(10);
  }

  /**
   * This helper method is used to grow the hash table once the load capacity is greater than 80%.
   * It creates a new array of linked lists that has twice the capacity as the old one. It then goes
   * through each node in the linked list in each of the indexes of the array and rehashes the key
   * to find its position in the new array.
   * 
   * @return the hash table that is double in size with all the values rehashed
   */
  private LinkedList<KeyValuePair<KeyType, ValueType>>[] growHashTable() {
    capacity *= 2;
    LinkedList<KeyValuePair<KeyType, ValueType>>[] newTable = new LinkedList[capacity];
    for (int i = 0; i < newTable.length; i++) {
      newTable[i] = new LinkedList();
    }
    for (int i = 0; i < hashTable.length; i++) {
      if (hashTable[i] != null) {
        for (int j = 0; j < hashTable[i].size(); j++) {
          KeyValuePair<KeyType, ValueType> currPair = hashTable[i].get(j);
          // finds the new index of the value by rehashing the key with the new hash table
          int hashIndex = hashFunction(currPair.getKey(), newTable);
          newTable[hashIndex].add(currPair);
        }
      }
    }
    return newTable;
  }

  /**
   * Helper method that returns the absolute value of the modulus of the hashCode of key with the
   * capacity of the hashTable.
   * 
   * @param key       - key that needs to be hashed
   * @param hashTable - used to find the capacity of the hashTable that the key-value pair is being
   *                  inserted into
   * @return int - index of the hashTable that the key hashes to
   */
  private int hashFunction(KeyType key, LinkedList<KeyValuePair<KeyType, ValueType>>[] hashTable) {
    return Math.abs(key.hashCode() % capacity);
  }

  /**
   * Checks if hashTable already contains key. If so it returns false. If not the key-value pair is
   * added to hashTable after using the hashFunction helper method, the size is also incremented.
   * Then the hashTable is checked to see if load capacity (size/capacity) is greater than or equal
   * to 0.8. If so a call if made to the growhashTable helper method which doubles the capacity of
   * the hashTable and then rehashes all the values. If the pair was added successfully the method
   * returns true.
   * 
   * @param key   - reference to key that needs to be added to hashTable
   * @param value - reference to value that needs to be added to hashTable
   * @see put method in MapADT interface
   * @return true - if the key-value pair has been successfully added to hashTable false - if
   *         hashTable already contains the key
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    if (containsKey(key)) {
      return false;
    } else {
      int hashIndex = hashFunction(key, hashTable);
      KeyValuePair<KeyType, ValueType> tempPair = new KeyValuePair<KeyType, ValueType>(key, value);
      hashTable[hashIndex].add(tempPair);
      ++size;
      if (Double.valueOf(size) / capacity >= 0.8) {
        hashTable = growHashTable();
      }
      return true;
    }
  }

  /**
   * Searches through the hashTable to find the value that is paired up with a specific key. The
   * method doesn't remove the key-value pair instead it just returns the value.
   * 
   * @see get method in MapADT interface
   * @param key - reference to key that is used to search the hashTable
   * @throws NoSuchElementException - if the hashTable doesn't contain the key specified by the
   *                                argument of the method.
   * @return ValueType - the value of key that is passed in as the argument of the method
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    if (!containsKey(key)) {
      throw new NoSuchElementException();
    } else {
      int hashIndex = hashFunction(key, hashTable);
      for (int i = 0; i < hashTable[hashIndex].size(); i++) {
        if (hashTable[hashIndex].get(i).getKey().equals(key)) {
          return (ValueType) hashTable[hashIndex].get(i).getValue();
        }
      }
      return null;
    }
  }

  /**
   * Returns the number of key-value pairs that are stored in hashTable
   * 
   * @see size method in MapADT interface
   * @return int - number of key-value pairs
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns the number of indexes in the hashTable array
   * 
   * @see size method in MapADT interface
   * @return int - number of indexes in array
   */
  public int capacity() {
    return capacity;
  }

  /**
   * Uses hashing and then a linear search to check whether the key specified by the argument of the
   * method is present or not in the hashTable.
   * 
   * @see containsKey method in MapADT interface
   * @param key - reference to key to check whether it is already present in hashTable
   * @return true - if the hashTable contains the key false - if the hashTable doesn't contain the
   *         key
   */
  @Override
  public boolean containsKey(KeyType key) {
    int hashIndex = hashFunction(key, hashTable);
    if (hashTable[hashIndex] == null || hashTable[hashIndex].size() == 0) {
      return false;
    } else {
      for (int i = 0; i < hashTable[hashIndex].size(); i++) {
        if (hashTable[hashIndex].get(i).getKey().equals(key)) {
          return true;
        }
      }
      return false;
    }
  }

  /**
   * Returns a reference to the value associated with the key that is being removed. If the key
   * being removed does not exist, the method will then return null.
   * 
   * @see remove method in MapADT interface
   * @param key - reference to key which needs to be removed from the hashTable
   * @return ValueType - the value of key that is removed from the hashTable. null - if hashTable
   *         doesn't contain the key that needs to be removed
   */
  @Override
  public ValueType remove(KeyType key) {
    if (!containsKey(key)) {
      return null;
    } else {
      int hashIndex = hashFunction(key, hashTable);
      for (int i = 0; i < hashTable[hashIndex].size(); i++) {
        if (hashTable[hashIndex].get(i).getKey().equals(key)) {
          ValueType value = (ValueType) hashTable[hashIndex].remove(i).getValue();
          --size;
          return value;
        }
      }
      return null;
    }
  }

  /**
   * Removes all the key-value pairs from hashTable and sets size to 0.
   * 
   * @see clear method in MapADT interface
   */
  @Override
  public void clear() {
    hashTable = new LinkedList[capacity];
    size = 0;
  }
}

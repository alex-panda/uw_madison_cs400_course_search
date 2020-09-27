// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: There are 4 source files in this project: MapADT.java, KeyValuePair.java,
// HashTableMap.java, TestHashTable.java.

import BackendDeveloper2.HashTableMap;

import java.util.NoSuchElementException;

public class TestHashTable {

  /**
   * This method checks whether the HashTableMap constructors functions as expected.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test1() {
    try {
      // checks the default constructor
      HashTableMap<Integer, String> test = new HashTableMap<Integer, String>();
      if (test.size() != 0 || test.getCapacity() != 10)
        return false;
      // checks the constructor
      test = new HashTableMap<Integer, String>(20);
      if (test.size() != 0 || test.getCapacity() != 20)
        return false;
    } catch (Exception e) { // no exception should occur in the constructors
      return false;
    }
    return true;
  }

  /**
   * This method checks whether the put(KeyType key, ValueType value) and size() methods in
   * HashTableMap functions as expected. Rehashing is not involved in this test.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test2() {
    try {
      // checks the size and capacity of the hashTable after adding some data
      HashTableMap<Integer, String> test = new HashTableMap<Integer, String>();
      if (test.size() != 0 || test.getCapacity() != 10)
        return false;
      test.put(13, "apple");
      if (test.size() != 1 || test.getCapacity() != 10)
        return false;
      test.put(27, "banana");
      if (test.size() != 2 || test.getCapacity() != 10)
        return false;
      if (test.put(30, "cow") != true)
        return false;
      // checks the functionality of chaining
      test.put(23, "dog");
      if (!test.get(13).equals("apple") || !test.get(23).equals("dog"))
        return false;
      test.put(23, "dog");
      if (test.size() != 4 || test.getCapacity() != 10)
        return false;
      // checks whether the data was added when key already exists in the hashTable
      test.put(23, "egg");
      if (test.size() != 4 || test.getCapacity() != 10 || !test.get(23).equals("dog"))
        return false;
      if (test.put(23, "dog") != false)
        return false;
    } catch (Exception e) { // no exception should occur in these tested methods
      return false;
    }
    return true;
  }

  /**
   * This method checks whether the put(KeyType key, ValueType value) and rehashing() methods in
   * HashTableMap functions as expected. The get(KeyType key) method is also tested here, but the
   * situation where no key matches is not considered in this test.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test3() {
    try {
      // creates a hash table that is going to be rehashed
      HashTableMap<Integer, String> test = new HashTableMap<Integer, String>(5);
      test.put(13, "apple");
      test.put(27, "banana");
      test.put(30, "cow");
      test.put(23, "dog");
      test.put(33, "egg");
      // checks the rehashing result
      if (test.getCapacity() != 10 || test.size() != 5 || !test.get(33).equals("egg"))
        return false;
      if (!test.get(13).equals("apple") || !test.get(27).equals("banana")
          || !test.get(23).equals("dog"))
        return false;
    } catch (Exception e) { // no exception should occur in these tested methods
      return false;
    }
    return true;
  }

  /**
   * This method checks whether the get(KeyType key) and containsKey(KeyType key) methods in
   * HashTableMap functions as expected. The situation where no key matches is considered in this
   * test.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test4() {
    // creates a hash table that is going to be tested
    HashTableMap<Integer, String> test = new HashTableMap<Integer, String>(17);
    test.put(13, "apple");
    test.put(27, "banana");
    test.put(30, "cow");
    // test whether the get method throws correct exception when needed
    try {
      test.get(23);
      return false;
    } catch (NoSuchElementException e) {

    } catch (Exception e) {
      return false;
    }
    // test whether the get method throws no exception when not needed
    test.put(23, "dog");
    test.put(23, "egg");
    try {
      if (!test.get(23).equals("dog"))
        return false;
    } catch (Exception e) {
      return false;
    }
    // test the containKey method
    try {
      if (test.containsKey(33) || !test.containsKey(27))
        return false;
    } catch (Exception e) { // no exception should occur in this tested method
      return false;
    }
    return true;
  }

  /**
   * This method checks whether the remove(KeyType key) and clear() methods in HashTableMap
   * functions as expected.
   *
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean test5() {
    try {
      // creates a hash table that is going to be tested
      HashTableMap<Integer, String> test = new HashTableMap<Integer, String>(99);
      test.put(13, "apple");
      test.put(27, "banana");
      test.put(30, "cow");
      test.put(23, "dog");
      test.put(33, "egg");
      // checks the remove method
      if (!test.remove(30).equals("cow") || test.size() != 4)
        return false;
      if (test.remove(3) != null)
        return false;
      // checks the clear method
      test.clear();
      if (test.size() != 0 || test.getCapacity() != 99)
        return false;
    } catch (Exception e) { // no exception should occur in these tested methods
      return false;
    }
    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("test1: " + test1());
    System.out.println("test2: " + test2());
    System.out.println("test3: " + test3());
    System.out.println("test4: " + test4());
    System.out.println("test5: " + test5());

  }

}

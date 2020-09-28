// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yulan BAO
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * A hash map that holds key:value pairs so that the values can be easily and swiftly looked up
 * using their key.
 * */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    private HashMapObject<?, ?>[] hashTable;
    private int numItemsInTable;

   /**
    * The default constructor for the HashTableMap which initializes the map
    * with a default capacity of 10.
    */
    public HashTableMap() {
        hashTable = (HashMapObject<KeyType, ValueType>[]) new HashMapObject[10];
        this.numItemsInTable = 0;
    }

   /**
    * Another constructor for the HashTableMap which initializes the map
    * with the given int capacity.
    * @param int capacity The starting capacity of the HashTableMap.
    */
    public HashTableMap(int capacity) {
        hashTable = (HashMapObject<KeyType, ValueType>[]) new HashMapObject[capacity];
        this.numItemsInTable = 0;
    }

   /**
    * Method to put a key-value pair into the hash map.
    * @param KeyType key the key to use to get the value.
    * @param ValueType value the value to store at the key.
    * @return false if the value was not put in the HashMap, true otherwise.
    */
    public boolean put(KeyType key, ValueType value) {
        int hashTableIndex = this.hashFunction(key, this.hashTable.length);
        HashMapObject<?,?> hashMapObj = this.hashTable[hashTableIndex];
        if (hashMapObj == null) {
            this.hashTable[hashTableIndex] = new HashMapObject<KeyType, ValueType>(key, value);
        } else {
            // Since there was a key collision, get to the end of the linked list (returning false
            //      if any of the HashMapObject keys on the way there are equal to the insert key)
            //      and add the new key/value to the end of the linked list.
            HashMapObject<?,?> lastHashMapObj = null;
            while (hashMapObj != null) {
                if (hashMapObj.getKey().equals(key)) {
                    return false;
                }
                lastHashMapObj = hashMapObj;
                hashMapObj = hashMapObj.getNextHashMapObject();
            }
            lastHashMapObj.setNextHashMapObject(new HashMapObject<KeyType, ValueType>(key, value));
        }

        this.numItemsInTable++;

        if (this.atCapacity()) {
            this.doubleArrayAndRehash();
        }
        return true;
    }

   /**
    * Method to get a value from the hash map.
    * @param KeyType key the key to get the desired value.
    * @return the value that was looked up.
    * @exception NoSuchElementException if there was no value for the given key.
    */
    public ValueType get(KeyType key) throws NoSuchElementException {
        int hashTableIndex = this.hashFunction(key, this.hashTable.length);
        HashMapObject<?,?> hashMapObj = this.hashTable[hashTableIndex];
        while (hashMapObj != null) {
            if (hashMapObj.getKey().equals(key)) {
                return (ValueType) hashMapObj.getValue();
            }
            hashMapObj = hashMapObj.getNextHashMapObject();
        }
        throw new NoSuchElementException("The given key had no value stored in the HashTableMap.");
    }

   /**
    * Method to get the size of the hashmap (how many key-value pairs are stored).
    */
    public int size() {
        return this.numItemsInTable;
    }

   /**
    * Method to see if the hashmap contains a value for the given key.
    * @param KeyType key the key to use to get the value.
    * @return true if the HashTableMap has a value for the key, false otherwise.
    */
    public boolean containsKey(KeyType key) {
        try {
            this.get(key);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

   /**
    * A method to remove a key/value from the hashmap.
    * @param KeyType key the key whose value should be removed from the hashmap.
    * @return the value that was removed.
    */
    public ValueType remove(KeyType key) {
        int hashTableIndex = hashFunction(key, hashTable.length);
        HashMapObject<?,?> hashMapObj = this.hashTable[hashTableIndex];
        HashMapObject<?,?> lastHashMapObj = hashMapObj;

        // Iterate through the linked list at the index until either the hashmap
        //      currently being inspected is null or has a key equal to the desired one.
        while (hashMapObj != null && !hashMapObj.getKey().equals(key)) {
            lastHashMapObj = hashMapObj;
            hashMapObj = hashMapObj.getNextHashMapObject();
        }

        // If the hashMapObj is null at this point, then one that matches the key was not found
        //      and thus nothing is removed.
        if (hashMapObj == null) {
            return null;
        } else {
            // The hashmap was found at the hashTable index and is possibly part of a linked list.
            //      If it is, then remove it from the linked list.
            if (lastHashMapObj.getKey().equals(hashMapObj.getKey()) && !hashMapObj.hasNextHashmapObject()) {
                // The hashMapObj was NOT part of a linked list, so can just be deleted.
                this.hashTable[hashTableIndex] = null;
            } else {
                // The hashMapobj WAS part of a linked list, so that must be dealt with.
                lastHashMapObj.setNextHashMapObject(hashMapObj.getNextHashMapObject());
                hashMapObj.setNextHashMapObject(null);
            }
        }

        this.numItemsInTable--;
        return (ValueType) hashMapObj.getValue();
    }

   /**
    * Clears out the hashmap so that it no longer has any keys/values.
    */
    public void clear() {
        this.hashTable = new HashMapObject[10];
        this.numItemsInTable = 0;
    }

    // Helper methods

   /**
    * A helper method to double and rehash the Array.
    * */
    private void doubleArrayAndRehash() {
        HashMapObject<?,?>[] oldHashTable = this.hashTable;

        // Makes an array that has twice the capacity of the old one and then
        //      assigns it to the main hash table so that the this.put function
        //      can be used to populate it from the old hash table.
        this.hashTable = new HashMapObject[this.hashTable.length * 2];
        // Reset the numItemsInTable var because the put method will add
        //      add to it every time a hashcode is put into the new array.
        this.numItemsInTable = 0;

        for (int i = 0; i < oldHashTable.length; i++) {
            HashMapObject<?,?> hashObj = oldHashTable[i];
            while (hashObj != null && hashObj instanceof HashMapObject) {
                this.put((KeyType) hashObj.getKey(), (ValueType) hashObj.getValue());
                hashObj = hashObj.getNextHashMapObject();
            }
        }
    }

   /**
    * A method that returns true if the load capacity becomes >= 80%
    * @return true if this HashTable map is at capacity and false if it is not.
    * */
    private boolean atCapacity() {
        if ((((double)this.numItemsInTable/(double)hashTable.length)) >= 0.8) {
            return true;
        } else {
            return false;
        }
    }

   /**
    * A method that returns the HashTable index for a given key.
    * @param key the key to get the index for.
    * @param tableSize the size/length of the table you are getting the hashfunction for.
    * @return the hashTable index for the given key.
    * */
    private int hashFunction(KeyType key, int tableSize) {
        int hashCode = key.hashCode();
        // Make sure that the hashCode is positive
        if (hashCode < 0) {
            hashCode = hashCode * (-1);
        }
        // Make sure that the tableSize is positive
        if (tableSize < 0) {
            tableSize = tableSize * (-1);
        }
        return (hashCode % (tableSize - 1));
    }
}

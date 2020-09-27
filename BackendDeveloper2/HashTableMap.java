// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;

/**
 * This class define an array which stores hash map elements
 * @implement MapADT  
 * @author Yuan Chen
 */
public class HashTableMap<K,V> implements MapADT<K, V>{
   private int capacity;
   private int size = 0;
   private int index;   
   private double loadFactor = 0.8;
   //the array used to store the hash nodes with keys and values
   //each element is a linked list to handle hash collision
   private HashChain<K,V>[] mapList;
   
   /**
    * default constructor
    */
   @SuppressWarnings("unchecked")
   public HashTableMap() {
      this.capacity = 10;
      mapList = new HashChain[capacity];//[capacity];
      //add HashChain object to each slot of the array
      for (int i = 0; i < capacity; i++) {
         mapList[i] = new HashChain<K,V>();
      }
   }
   
   /**
    * constructor
    * @param capacity of the hash table
    */
   @SuppressWarnings("unchecked")
   public HashTableMap(int capacity) {
      this.capacity = capacity;
      mapList = new HashChain[capacity];
      for (int i = 0; i < capacity; i++) {
         mapList[i] = new HashChain<K,V>();
      }
   }
   
   /**
    * This method stores a hash key and value in the array
    * if hash collision occurs, it add the current hash to the end of existing list 
    */
   @Override
   public boolean put(K key, V value) {
      if (key == null || value == null) {
         return false;
      }
      index = Math.abs(key.hashCode() % this.capacity);//calculate the hash code of current key
      HashNode<K,V> newNode = new HashNode<K, V>(key, value);
      if (!mapList[index].contains(key)) {//if the same key is not already exist
         (mapList[index]).add(newNode);//add new hash
         this.size++;//increment of size
         if (size >= (int)(this.capacity * loadFactor)) {//check if the size reach 80% of the capacity
            grow();//if so, grow the array
         }
         return true;//return true if a hash is added to the array
      }
      else {return false;}//return false if no array is added
   }
   
   /**
    * This method doubles the capacity of current array,
    * copy every hash in the old array into the new one,
    * link the new array to the old array variable. 
    */
   public void grow() {
      this.capacity *= 2; //double the capacity
      @SuppressWarnings("unchecked")
      HashChain<K,V>[] newMapList = new HashChain[this.capacity];
      for (int i = 0; i < this.capacity; i++) {
         newMapList[i] = new HashChain<K, V>();
      }
      //add all the hash elements in the old array to the new one
      for (HashChain<K, V> c : mapList) {
         if (!c.isEmpty()) {
            for (HashNode<K, V> temp = c.getHead(); temp != null; temp = temp.getNext()) {
               index = Math.abs(temp.getKey().hashCode() % capacity);
               newMapList[index].add(temp);
            }
         }         
      }
      //link the new array to the old array variable
      this.mapList = newMapList;
   }
   
   /**
    * This method find the value with the given key in the hash table array
    * @throws NoSuchElementException() if the key doesn't exist in the current array
    * @return value linked to the key
    */
   @Override
   public V get(K key) throws NoSuchElementException {
      index = Math.abs(key.hashCode() % this.capacity);//calculate the hash code of current key
      V value = mapList[index].get(key);      
      if (value == null) {
         throw new NoSuchElementException();
      }
      return value;
   }
   
   /**
    * @return size
    */
   @Override
   public int size() {
      return this.size;
   }
   
   /**
    * This method check if the array contains the hash with the given key
    * @return true if yes, false if no.
    */
   @Override
   public boolean containsKey(K key) {
      if (key != null) {
         index = Math.abs(key.hashCode() % this.capacity);
         return mapList[index].contains(key);}
      else {return false;}
   }
   
   /**
    * This method removes the hash with the given key
    * @return value of the hash if it's removed successfully
    * @return null if there is no such hash with the given key
    */
   @Override
   public V remove(K key) {
      index = Math.abs(key.hashCode() % this.capacity);
      if (mapList[index] == null) {
         return null;
      }
      else {
         V value = mapList[index].remove(key);
      return value;
      }//return null if linked list is empty
   }
   
   /**
    * This method give a new memory address to the array,
    * effectively empty the array.
    * reset the size to 0.
    */
   @SuppressWarnings("unchecked")
   @Override
   public void clear() {
      this.mapList = new HashChain[this.capacity];
      for (int i = 0; i < capacity; i++) {
         mapList[i] = new HashChain<K, V>();
      }
      this.size = 0;
   }
}

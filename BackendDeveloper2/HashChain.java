// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class represents a double linked list (chaining) of HashNode
 * @author Yuan Chen
 */
public class HashChain<K,V> {
   private HashNode<K,V> head;//head of the list
   private HashNode<K,V> tail;//tail of the list
   private int size;//number of elements
   
   /**
    * default constructor
    */
   public HashChain() {
      this.head = null;
      this.tail = null;
      this.size = 0;
   }   
   
   /**
    * This method adds new element to the end of the list
    * @param element new element of the list
    */
   public void add(HashNode<K,V> node) {
      if (this.head == null) {//if the list is empty, set the new element to head and tail
         this.head = node;
         this.tail = node;
      } else {
         //HashNode<K, V> temp = this.tail;
         this.tail.setNext(node);
         node.setPrev(this.tail);
         this.tail = node;
         node.setNext(null);
      }
      size++;
   }
   
   /**
    * @return true if the chain has no node
    */
   public boolean isEmpty() {
      if (this.head == null) {
         return true;
      }
      else {return false;}
   }
   
   /**
    * This method looks for the index of the node with the specified key
    * @param key
    * @return the index of the node with the key, if no index is found, return -1
    */
   public int indexOf(K key) {
      int index = 0;
      if (key != null) {
         //loop through the chain to find the index
         for (HashNode<K,V> n = this.head; n !=null ; n = n.getNext()) {
            if (n.getKey().equals(key)) {//compare the node key with the given key
               return index;
            }
            index++;
         }         
      }
      return -1;
   }   
   
   /**
    * This method checks if the chain contains the specified key
    * @param key
    * @return true if a non -1 index is found
    */
   public boolean contains(K key){
      return indexOf(key) > -1;
   }
   
   /**
    * This method gets the value corresponding to the key
    * @param key
    * @return value, if no key-value pair is found, return null
    */
   public V get(K key) {
      if (key != null) {
         for (HashNode<K,V> n = this.head; n !=null ; n = n.getNext()) {
            if (n.getKey().equals(key)) {
               return n.getValue();
            }
         }         
      }
      return null;
   }
   
   /**
    * This method removes the node with the specified key
    * @param key
    * @return value of the removed node, null if no such node is found or removed
    */
   public V remove(K key) {
      V value;
      if (key != null) {
         for (HashNode<K,V> n = this.head; n !=null ; n = n.getNext()) {
            if (n.getKey().equals(key)) {
               value = n.getValue();
               //unlink the node
               if(n.getPrev() == null) {                  
                  this.head = n.getNext();
                  if (n.getNext() != null) {
                     n.getNext().setPrev(null);
                  }
               }
               else if(n.getNext() == null) {
                  n.getPrev().setNext(null);
               }
               else {
                  n.getPrev().setNext(n.getNext());
                  n.getNext().setPrev(n.getPrev());
               }
               size--;
               return value;
               
            }
         }         
      }
      return null;
   }
   
   /**
    * @return head of the chain
    */
   public HashNode<K,V> getHead() {
      return this.head;
   }
   
   /**
    * @return size
    */
   public int size() {
      return size;
   }
   

   
}
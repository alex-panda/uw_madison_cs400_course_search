// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class represents a HashElement type object which contents the
 * information of key and value
 * @author Yuan Chen
 *
 */
public class HashNode<K,V> {
   private K key;
   private V value;
   private HashNode<K,V> next;
   private HashNode<K,V> prev;
   
   /**
    * Constructor
    * @param key
    * @param value
    */
   public HashNode(K key, V value){
      this.key= key;
      this.value = value;
      this.next = null;
      this.prev = null;
   }
   
   /**
    * getter
    * @return key
    */
   public K getKey() {
      return this.key;
   }
   
   /**
    * getter
    * @return value
    */
   public V getValue() {
      return this.value;
   }
   
   /**
    * @return a reference to next node object after this node
    */
   public HashNode<K,V> getNext() {
      return next;
   }

   /**
    * @param next a reference to the node object that comes after this
    */
   public void setNext(HashNode<K,V> next) {
      this.next = next;
   }
   
   /**
    * @return a reference to next node object after this node
    */
   public HashNode<K,V> getPrev() {
      return this.prev;
   }

   /**
    * @param next a reference to the node object that comes after this
    */
   public void setPrev(HashNode<K,V> prev) {
      this.prev = prev;
   }
}

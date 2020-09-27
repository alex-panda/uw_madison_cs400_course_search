// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yulan BAO
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * An object to hold a key and its value for a HashTableMap.
 * It also holds the next HashMapObject (if there is a next) in order to create a
 * linked list of HashMapObjects in the event of a collision.
 * */
public class HashMapObject<KeyType, ValueType> {

    private KeyType key;
    private ValueType value;
    private HashMapObject<?, ?> nextHashObj = null;

   /**
    * Constructs a new HashMapObject.
    * */
    public HashMapObject(KeyType key, ValueType value) {
        this.key = key;
        this.value = value;
    }

   /**
    * Gets the key for this hashMapObj.
    * @return The key for this HashMapObject.
    * */
    public KeyType getKey() {
        return this.key;
    }

   /**
    * Gets the value that this object holds.
    * @return The value for this HashMapObject.
    * */
    public ValueType getValue() {
        return this.value;
    }

   /**
    * Sets the value that this object holds.
    * */
    public void setValue(ValueType newValue) {
        this.value = newValue;
    }

   /**
    * Gets the next HashMapObject in the linked list (if there is a next).
    * @return the next HashMapObject in the linked list or null if there is no next one.
    * */
    public HashMapObject<?, ?> getNextHashMapObject() {
        return this.nextHashObj;
    }

   /**
    * Returns true if the HashMapObject has a next HashMapObject, false otherwise.
    * @return true if the HashMapObject has a next HashMapObject, false otherwise.
    * */
    public boolean hasNextHashmapObject() {
        if (nextHashObj == null) {
            return false;
        } else {
            return true;
        }
    }

   /**
    * Sets the next HashMapObject in the linked list.
    * @param hashObj the next HashMapObject in the linked list.
    * */
    public void setNextHashMapObject(HashMapObject<?,?> hashObj) {
        this.nextHashObj = hashObj;
    }
}

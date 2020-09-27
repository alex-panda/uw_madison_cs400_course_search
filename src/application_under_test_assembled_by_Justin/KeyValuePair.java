// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: There are 4 source files in this project: MapADT.java, KeyValuePair.java,
// HashTableMap.java, TestHashTable.java.

/**
 * This class models a key-value pair for the HashTableMap. It only has the basic constructors,
 * getters, and setters.
 *
 * @author Justin Qiao
 */
public class KeyValuePair<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;

  public KeyValuePair(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  public KeyType getKey() {
    return key;
  }

  public void setKey(KeyType key) {
    this.key = key;
  }

  public ValueType getValue() {
    return value;
  }

  public void setValue(ValueType value) {
    this.value = value;
  }



}

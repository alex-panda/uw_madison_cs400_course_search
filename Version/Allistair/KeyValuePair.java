// --== CS400 File Header Information ==--
// Name: Allistair Nathan Mascarenhas
// Email: anmascarenha@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class is used to keep track of the key and value pairs that are present in the hash table.
 */
public class KeyValuePair<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;

  /**
   * Constructor that initializes the key and value variables with the arguments that were passed
   * in.
   * 
   * @param key   - key that needs to be hashed
   * @param value - value that needs to be stored in the hash table
   */
  public KeyValuePair(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }

  /**
   * Accessor method that is used to return the key in a key-value pair.
   * 
   * @return key - KeyType
   */
  public KeyType getKey() {
    return this.key;
  }

  /**
   * Accessor method that is used to return the value in a key-value pair.
   * 
   * @return value - ValueTYpe
   */
  public ValueType getValue() {
    return this.value;
  }
}

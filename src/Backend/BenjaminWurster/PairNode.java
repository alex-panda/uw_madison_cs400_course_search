package BackendDeveloper;// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

/**
 * Object that pairs the KeyType generic and the ValueType generic into one
 * object.
 * 
 * @author Ben
 *
 * @param <KeyType>   generic type for key
 * @param <ValueType> generic type for returned value
 */
public class PairNode<KeyType, ValueType> {
	private KeyType key;
	private ValueType value;

	public PairNode(KeyType key, ValueType value) {
		this.key = key;
		this.value = value;
	}

	public KeyType getKey() {
		return key;
	}

	public ValueType getValue() {
		return value;
	}
}

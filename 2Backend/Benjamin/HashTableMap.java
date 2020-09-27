// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.NoSuchElementException;
import java.util.LinkedList;

/**
 * HashTableMap, MapADT interface implementation for key-value pairs
 * 
 * @author Ben
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	private int capacity;
	private LinkedList<PairNode<KeyType, ValueType>>[] hashTable;
	private int size;

	/**
	 * Constructor for hash table. Initializes fields.
	 * 
	 * @param capacity The initial capacity of the table
	 */
	@SuppressWarnings("unchecked")
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		hashTable = new LinkedList[this.capacity];
		this.size = 0;
	}

	/**
	 * Default constructor
	 */
	public HashTableMap() { // with default capacity = 10
		this(10);
	}

	/**
	 * Private hash function to reduce redundancy of code.
	 * 
	 * @param key the key to hash
	 * @return array index in hash table
	 */
	private int hash(KeyType key) {
		return Math.abs(key.hashCode() % this.capacity);
	}

	/**
	 * Private helper method to rehash the table
	 */
	@SuppressWarnings("unchecked")
	private void rehashTable() {
		LinkedList<PairNode<KeyType, ValueType>>[] oldHashTable = hashTable;
		this.capacity *= 2;
		this.hashTable = new LinkedList[this.capacity];
		for (LinkedList<PairNode<KeyType, ValueType>> list : oldHashTable) {
			if (list != null) {
				for (PairNode<KeyType, ValueType> pair : list) {
					this.put(pair.getKey(), pair.getValue());
					this.size--;
				}
			}

		}
	}

	/**
	 * Put method to insert key-value pairs into the table. Rehash if size reaches
	 * 80% of capacity.
	 * 
	 * @return true if successfully inserted
	 */
	@Override
	public boolean put(KeyType key, ValueType value) {
		if (!this.containsKey(key)) {
			if (hashTable[hash(key)] == null) {
				hashTable[hash(key)] = new LinkedList<>();
			}
			hashTable[hash(key)].add(new PairNode<>(key, value));
			this.size++;
			if ((double) this.size / this.capacity >= 0.8) {
				rehashTable();
			}
			return true;
		}
		return false;
	}

	/**
	 * Get key from hash table if it exists
	 * 
	 * @return ValueType if key-value pair in table
	 * @throws NoSuchElementException if there is no element
	 */
	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		LinkedList<PairNode<KeyType, ValueType>> list = this.hashTable[hash(key)];
		if (list == null) {
			throw new NoSuchElementException();
		} else {
			for (PairNode<KeyType, ValueType> pair : list) {
				if (pair.getKey().equals(key)) {
					return pair.getValue();
				}
			}
			throw new NoSuchElementException();
		}
	}

	/**
	 * @return size of elements in hash table
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * checks to see of hash table contains key
	 * 
	 * @returns true if hash table has key
	 */
	@Override
	public boolean containsKey(KeyType key) {
		try {
			this.get(key);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}

	/**
	 * Remove an element from the hash table and return its value
	 * 
	 * @return ValueType associated with removed element
	 */
	@Override
	public ValueType remove(KeyType key) {
		if (this.containsKey(key)) {
			LinkedList<PairNode<KeyType, ValueType>> list = hashTable[hash(key)];
			for (PairNode<KeyType, ValueType> pair : list) {
				if (pair.getKey().equals(key)) {
					list.remove(pair);
					size--;
					return pair.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * Clear the hash table
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.hashTable = new LinkedList[this.capacity];
		this.size = 0;
	}

}

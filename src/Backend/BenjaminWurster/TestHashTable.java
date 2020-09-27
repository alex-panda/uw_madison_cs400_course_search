package BenjaminWurster;
// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.NoSuchElementException;

/**
 * Test class for HashTableMap
 *
 * @author Ben
 *
 */
public class TestHashTable {

	/**
	 * Test put(), size(), rehashTable() by adding to the table, checking
	 * correctness of size and ensuring that the size remains accurate after a
	 * rehashing point. The existence of all elements is then checked to make sure
	 * they are transferred correctly.
	 *
	 * @return true if passed
	 */
	public static boolean test1() {
		boolean passed = true;

		HashTableMap<Integer, String> table = new HashTableMap<>();

		for (int i = 0; i < 10; i++) {
			table.put(i, "String Key: " + i);
		}
		if (table.size() != 10) {
			passed = false;
			System.out.println("test1() did not return correct size.");
		}

		if(table.put(1, "String Key: 1")) {
			passed = false;
		}
		if(table.put(1, "Different String, Same Key")) {
			passed = false;
		}

		for (int i = 0; i < 10; i++) {
			if (!table.containsKey(i)) {
				passed = false;
			}
		}

		return passed;
	}

	/**
	 * Test get() by getting a valid value and checking its accuracy and by trying
	 * to get an invalid value and seeing that an exception is thrown.
	 *
	 * @return true if passed
	 */
	public static boolean test2() {
		boolean passed = true;

		HashTableMap<Integer, String> table = new HashTableMap<>();

		for (int i = 0; i < 10; i++) {
			table.put(i, "String Key: " + i);
		}

		if (!table.get(5).equals("String Key: 5")) {
			passed = false;
			System.out
					.println("test2(), value incorrect. Returned value type was: " + table.get(5));
		}

		try {
			table.get(20);
			passed = false;
			System.out.println("test2(), Exception not caught");
		} catch (NoSuchElementException e) {
			// Do nothing
		}

		return passed;
	}

	/**
	 * Test containsKey() by adding to the table and checking the accuracy of
	 * existent and non-existent values.
	 *
	 * @return true if passed
	 */
	public static boolean test3() {
		boolean passed = true;

		HashTableMap<Integer, String> table = new HashTableMap<>();

		for (int i = 0; i < 10; i++) {
			table.put(i, "String Key: " + i);
		}

		if (!table.containsKey(3)) {
			passed = false;
		}

		if (table.containsKey(12)) {
			passed = false;
		}

		return passed;
	}

	/**
	 * Tests remove() by checking that an element can be correctly removed, checking
	 * that a removed element cannot be removed again, and checking the accuracy of
	 * attempted removal from an empty table.
	 *
	 * @return true if passed
	 */
	public static boolean test4() {
		boolean passed = true;

		HashTableMap<Integer, String> table = new HashTableMap<>();

		for (int i = 0; i < 10; i++) {
			table.put(i, "String Key: " + i);
		}

		String value = table.remove(1);
		if (!value.equals("String Key: 1")) {
			passed = false;
			System.out.println("Incorrect return value");
		}
		if (table.size() != 9) {
			passed = false;
			System.out.println("Incorrect size");
		}

		// Try to remove removed element, both to check that it is gone and for other
		// errors with remove()
		value = table.remove(1);
		if (value != null) {
			passed = false;
			System.out.println("Incorrect return value");
		}
		if (table.size() != 9) {
			passed = false;
			System.out.println("Incorrect size");
		}

		// New table to check empty table
		table = new HashTableMap<>();

		value = table.remove(1);
		if (value != null) {
			passed = false;
			System.out.println("Incorrect return value");
		}
		if (table.size() != 0) {
			passed = false;
			System.out.println("Incorrect size");
		}

		return passed;
	}

	/**
	 * Test clear() and different generic type functionality by adding String,
	 * Double pairs, checking size, running clear() ad checking that elements are
	 * clear.
	 *
	 * @return true if passed
	 */
	public static boolean test5() {
		boolean passed = true;

		HashTableMap<String, Double> table = new HashTableMap<>();

		for (int i = 0; i < 10; i++) {
			table.put("String Key: " + (i * 0.5), i * 0.5);
		}
		if (table.size() != 10) {
			passed = false;
			System.out.println("Incorrect size");
		}

		table.clear();

		if (table.size() != 0) {
			passed = false;
			System.out.println("Table not cleared");
		}
		if (table.remove("String Key: 0.5") != null) {
			passed = false;
		}

		return passed;
	}

	/**
	 * Main method to run tests.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("test1(): " + test1());
		System.out.println("test2(): " + test2());
		System.out.println("test3(): " + test3());
		System.out.println("test4(): " + test4());
		System.out.println("test5(): " + test5());
	}

}

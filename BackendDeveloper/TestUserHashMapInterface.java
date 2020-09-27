// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.NoSuchElementException;

/**
 * Test bench for UserHashMapInterface.java
 * 
 * @author Ben
 *
 */
public class TestUserHashMapInterface {
	/**
	 * Tests the accuracy of the initial data loading
	 * 
	 * @return true if tests pass
	 */
	private static boolean testDataLoader() {
		boolean passed = true;
		UserHashMapInterface database = new UserHashMapInterface();
		try {
			for (int i = 0; i < 400; i += 20) {
				String name = "CS" + (200 + i);

				if (!database.getCourse(name).getName().equals(name)) {
					passed = false;
				}
			}
		} catch (NoSuchElementException e) {
			passed = false;
		} catch (NullPointerException e) {
			passed = false;
		}

		return passed;
	}

	/**
	 * Tests the functionality of the add method.
	 * 
	 * @return true if tests pass
	 */
	private static boolean testAdd() {
		boolean passed = true;
		UserHashMapInterface database = new UserHashMapInterface();
		
		database.addCourse(new Course("CS100", null, null));
		
		if (!database.getCourse("CS100").getName().equals("CS100")) {
			passed = false;
		}
		
		if(database.addCourse(new Course("CS100", null, null))) {
			passed = false;
		}
		
		return passed;
	}
	
	/**
	 * Tests the functionality of the remove method
	 * 
	 * @return true if tests pass
	 */
	private static boolean testRemove() {
		boolean passed = true;
		UserHashMapInterface database = new UserHashMapInterface();
		
		database.addCourse(new Course("CS100", null, null));
		
		if(!database.removeCourse("CS100")) {
			passed = false;
		}
		
		if(database.removeCourse("CS100")) {
			passed = false;
		}
		
		return passed;
	}
	
	/**
	 * Tests getting a non-existent element
	 * 
	 * @return true if tests pass
	 */
	private static boolean testNoElementGet() {
		boolean passed = true;
		UserHashMapInterface database = new UserHashMapInterface();
		
		Course course = database.getCourse("CS100");
		if(course != null) {
			passed = false;
		}
		
		return passed;
	}
	
	/**
	 * Tests accuracy of hash table size
	 * 
	 * @return true if tests pass
	 */
	private static boolean testSize() {
		boolean passed = true;
		UserHashMapInterface database = new UserHashMapInterface();
		
		if (database.courseCount() != 20) {
			passed = false;
		}
		
		return passed;
	}
	
	
	/**
	 * Main method to run tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("testDataLoader():" + testDataLoader());
		System.out.println("testAdd():" + testAdd());
		System.out.println("testRemove():" + testRemove());
		System.out.println("testNoElementGet():" + testNoElementGet());
		System.out.println("testSize():" + testSize());
	}
}

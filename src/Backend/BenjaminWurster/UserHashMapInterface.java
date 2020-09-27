package BenjaminWurster;
// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The Backend Developer 1 UserHashMapInterface to link frontend to individual
 * hash maps
 *
 * @author Ben
 *
 */
public class UserHashMapInterface {

	private HashTableMap<String, Course> table;

	/**
	 * Constructor to create table instance and call an initialization method
	 */
	public UserHashMapInterface() {
		this.table = new HashTableMap<>();

		initialize();
	}

	/**
	 * Adds a course to the hash table. Returns false if does not add because
	 * element is already present.
	 *
	 * @param course The course to be added
	 * @return true if element is added successfully
	 */
	public boolean addCourse(Course course) {
		return this.table.put(course.getName(), course);
	}

	/**
	 * Removes a course from the table
	 *
	 * @param courseName The name of the course to remove
	 * @return true of successfully removed
	 */
	public boolean removeCourse(String courseName) {
		Course pulledCourse = table.remove(courseName);
		if (pulledCourse == null) {
			return false;
		}
		return true;
	}

	/**
	 * A method to access the size of the table
	 *
	 * @return number of courses in table
	 */
	public int courseCount() {
		return table.size();
	}

	/**
	 * Gets a course object from the table
	 *
	 * @param courseName The name of the course to get
	 * @return course object with matching name
	 */
	public Course getCourse(String courseName) {
		try {
			return table.get(courseName);
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	/**
	 * private helper method to fill the hash table with existing data
	 */
	private void initialize() { // calls Data wrangler initialization
		TempDataLoader dataLoader = new TempDataLoader(); // TODO remove Temp when integrated
		ArrayList<Course> courseList = dataLoader.getData();
		for (Course course : courseList) {
			addCourse(course);
		}
	}

}

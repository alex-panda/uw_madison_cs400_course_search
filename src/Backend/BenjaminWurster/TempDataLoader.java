package BenjaminWurster;
// --== CS400 File Header Information ==--
// Name: Benjamin Ryan Wurster
// Email: bwurster@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.ArrayList;

/**
 * A dummy data loader to use for testing UserHashMapInterface.java
 * @author Ben
 *
 */
public class TempDataLoader {
	private ArrayList<Course> coursesList;

	/**
	 * Constuctor to create fake data
	 */
	public TempDataLoader() {
		this.coursesList = new ArrayList<>();
		for(int i = 0; i < 400; i += 20) {
			ArrayList<Object> prereqs = new ArrayList<>();
			prereqs.add("CS" + (200 + (i - 1)));
			prereqs.add("CS" + (200 + (i - 2)));
			prereqs.add("CS" + (200 + (i - 3)));

			ArrayList<String> noreqs = new ArrayList<>();
			noreqs.add("CS" + (200 + (i - 4)));

			Course course = new Course("CS" + (200 + i), prereqs, noreqs);
			this.coursesList.add(course);
		}
	}

	/**
	 * method to call for the ArrayList of Course objects
	 *
	 * @return ArrayList of Course objects
	 */
	public ArrayList<Course> getData() {
		return this.coursesList;
	}
}

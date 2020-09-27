// --== CS400 File Header Information ==--
// Name: Allistair Mascarenhas
// Email: anmascarenha@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.ArrayList;

/**
 * The course class stores the name, prerequisites of that course and the courses that cannot be
 * taken before taking this course (norequisites)
 */
public class Course {
  private String name;
  private ArrayList<Object> prereqs;
  // In order to take a course you can not take course(s) specified by noreqs
  private ArrayList<String> noreqs;

  /**
   * Constructor that initializes all the private variables of the class
   * 
   * @param name    - name of the course
   * @param prereqs - prerequisites of the course
   * @param noreqs  - courses that can't be taken before taking this course
   */
  public Course(String name, ArrayList<Object> prereqs, ArrayList<String> noreqs) {
    this.name = name;
    this.prereqs = prereqs;
    this.noreqs = noreqs;
  }

  /**
   * Checks if the course has prerequisites
   * 
   * @return true if the course has prerequisites, and false otherwise
   */
  public boolean hasPrereqs() {
    if (prereqs.size() == 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Checks if the course has other courses that cannot be taken prior to this course
   * 
   * @return true if the course does have courses that cannot be taken prior to this course, and
   *         false otherwise
   */
  public boolean hasNoreqs() {
    if (noreqs.size() == 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Accessor method that returns the course name
   * 
   * @return String - course name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Accessor method that returns the prerequisites of the course
   * 
   * @return ArrayList<Object> - ArrayList of course prerequisites
   */
  public ArrayList<Object> getCoursePrereqs() {
    return this.prereqs;
  }

  /**
   * Accessor method that returns courses which cannot be taken before this course
   * 
   * @return ArrayList<String> - ArrayList of courses which cannot be taken before this course
   */
  public ArrayList<String> getCourseNoreqs() {
    return this.noreqs;
  }
}

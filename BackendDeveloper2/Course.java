import java.util.ArrayList;

/**
 * This class is built to test the function of Search object
 * it initialize a course object
 * @author Yuan Chen
 *
 */
public class Course {
   private String courseName;
   private ArrayList<Course> prereqs;
   
   /**
    * constructor
    * @param name of the course
    * @param prereqs a list of prerequisite courses
    */
   public Course(String name, ArrayList<Course> prereqs) {
      this.courseName = name;
      this.prereqs = prereqs;
   }
   
   /**
    * Getter
    * @return name of the course
    */
   public String getCourseName() {
      return courseName;
   }
   
   /**
    * mutator
    * @param name of the course 
    */
   public void setCourseName(String name) {
      this.courseName = name;
   }
   
   /**
    * Getter
    * @return prerequisites of the course
    */
   public ArrayList<Course> getPrereqs() {
      return prereqs;
   }
   
   /**
    * mutator
    * @param prereqs a list of prerequisite courses  
    */
   public void setPrereqs(ArrayList<Course> prereqs) {
      this.prereqs = prereqs;
   }
   
   /**
    * toString method
    * @return a String of the course Name plus the prerequisite courses
    */
   public String toString() {
      String courseDes;
      courseDes = "Course Name: " + this.courseName + "\nPrerequistes: ";
      for (Course c: prereqs) {
         courseDes = courseDes + c.getCourseName() + "_";
      }
      return courseDes;
   }
      

}

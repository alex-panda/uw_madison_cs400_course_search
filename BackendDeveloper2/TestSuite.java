import java.util.ArrayList;

/**
 * This class is built to test the function of Search object
 * @author Yuan Chen
 *
 */
public class TestSuite {
   public static void main(String[] args) {
      ArrayList<Course> prereqs = new ArrayList<Course>();//make a list of prerequisites
      String courseName = "CS400";//define the course name
      Course CS400 = new Course(courseName, prereqs);//initialize a new course object
      ArrayList<Course> courseList = new ArrayList<Course>();//initialize a data base of courses
      
      courseList.add(CS400);//add CS400 into the data base
      
      Search newSearch = new Search(courseList);//initialize a search object
                                                //turn database into a hash table
      
      System.out.println(newSearch.lookup("CS400"));//look up the CS400
      newSearch.lookup("CS300");//look up a course which is not in the database
      
      
   }
   
   
}

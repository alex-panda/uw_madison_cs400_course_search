import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * This class add data base into a hash table, and can search through the hash
 * table for a specified course
 * 
 * @author Yuan Chen
 *
 */
public class UserHashMapInterface {
   // initialize a new hash table map
   private HashTableMap<Integer, Course> courseMap;
   // the course which will be return to front end
   private Course searchResult;
   // hash key
   private Integer key;

   /**
    * constructor get data from data wrangler, hash and store them into a hash
    * table map
    * 
    * @param data course data
    */

   public UserHashMapInterface() {
      DataLoader newLoader = new DataLoader();
      ArrayList<Course> data = newLoader.getData();
      this.courseMap = new HashTableMap<Integer, Course>();
      // loop through the data and store the course object
      for (Course c : data) {
         key = c.getName().hashCode();
         courseMap.put(key, c);
      }
   }

   /**
    * This method get course name from the front end and search
    * 
    * @param courseName from user
    * @return course object
    */
   public Course getCourse(String courseName) {
      key = courseName.hashCode();
      // Not sure if we should throw exception here or in the menu from front end
      try {
         searchResult = courseMap.get(key);
      } catch (NoSuchElementException e) {
         //System.out.println("Course does not exist, please try again.");
         return null;
      }
      return this.searchResult;
   }
   
   /**
    * This method check if a course is in the data base/hash table
    * @param name of the course
    * @return true if course is in the data base/hash table
    */
   public boolean hasCourse(String name) {
      int key = name.hashCode();
      if (courseMap.containsKey(key)) {
         return true;
      } else {
         return false;
      }
   }
   
   /**
    * This method printout all the courses offered at CS department
    */
   public void print() {   
      ArrayList<String> courses = new ArrayList<>();
      int capacity = this.courseMap.getCap();
      HashChain<Integer, Course>[] c = courseMap.getList();
      for (int i = 0; i < capacity; i++) {
         if (c[i].getHead() != null) {
            //add the name value of the head node to the list if not null
            for (HashNode<Integer, Course> temp = c[i].getHead(); temp != null; temp = temp.getNext()) {
               courses.add(temp.getValue().getName());
            }
         }
      }
      Collections.sort(courses);
      System.out.println("The following courses are offered at UW-Madison Computer Science Department:");
      for (String name: courses) {
         System.out.println(name);
      }
   }

}

import java.util.ArrayList;

/**
 * This class is built to test the function of Search object
 * @author Yuan Chen
 *
 */
public class TestSuite2 {
   public static void main(String[] args) {
//      ArrayList<Object> prereqs = new ArrayList<Object>();//make a list of prerequisites
//      String courseName = "CS400";//define the course name
//      Course CS400 = new Course(courseName, prereqs);//initialize a new course object
//      ArrayList<Course> courseList = new ArrayList<Course>();//initialize a data base of courses
//      
//      courseList.add(CS400);//add CS400 into the data base
//      
//      UserHashMapInterface newSearch = new UserHashMapInterface();//initialize a search object
//                                                //turn database into a hash table
//      
//      System.out.println(newSearch.getCourse("CS400"));//look up the CS400
//      newSearch.getCourse("CS300");//look up a course which is not in the database
      
//      String key = "MAP526";
//      System.out.println(Math.abs(key.hashCode()));
//      HashTableMap test = new HashTableMap();
//      for (int i = 0; i < 1000; i++) {
//         test.put("Map" + i, "i: " + i);
//      }
//      for (int i = 0; i < 1000; i++) {
//         String value = (String) test.remove("Map" + i);
//         //if (value != ("i: " + i)) {
//         if (!value.equals("i: " + i)) {
//             System.out.print("Failed: The hashmap did not return the correct value for " +
//                     "" + "Map" + i +". Returned value: " + value + "\n");
//         }
//      }
//      
      
      HashChain test1 = new HashChain();
      test1.add(new HashNode(1, 2));
      test1.add(new HashNode(2, 4));
      test1.add(new HashNode(3, 6));
      test1.add(new HashNode(4, 8));
      System.out.println(test1.remove(1));
      System.out.println(test1.remove(2));
      System.out.println(test1.remove(3));
      System.out.println(test1.remove(4));
   }
   
   
}

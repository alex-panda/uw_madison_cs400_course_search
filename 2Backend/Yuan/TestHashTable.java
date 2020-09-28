

// --== CS400 File Header Information ==--
// Name: Yuan Chen   
// Email: chen2243@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

/**
 * This class is the main class used to test the HashTableMap class
 * @author Yuan Chen
 *
 */
public class TestHashTable {
   
   /**
    * This method tests the put method
    * @param map
    * @return true if the hash is added to the array
    */
   public static boolean test1(HashTableMap<String, String> map) {
      map.put("first", "First Object"); //add a hash
      if (!map.containsKey("first")) {
         return false;//return false if array do not contain key "first"
      }
      else if (!(map.get("first").equals("First Object"))) {
         return false;//return false if the value of the hash is not "First Object"
      }
      else if(map.put("first", "Same Object again")) {
         return false;//return false if another hash with the same key can be put
      }
      else if(map.get("first").equals("Same Object again")) {
         return false;//return false if the value changed to the new value
      }
      boolean a = map.put(null, null);//test if put add null value to the hashchain
      if (a == true) {
         return false;
      }
      return true;
   }
   
   /**
    * this method tests the size method
    * @param map
    * @return true if size is added correctly
    */
   public static boolean test2(HashTableMap<Integer, String> map) {
      map.put(1, "obj1");
      map.put(2, "obj2");
      map.put(3, "obj3");
      if (map.size() == 3) {
         return true;//return true if the size is added correctly
      }
      else {return false;}
   }
   
   /**
    * this method tests the grow method
    * @param map
    * @return true if size is added correctly
    */
   public static boolean test3(HashTableMap<Integer, String> map) {
      map.put(1, "obj1");
      map.put(2, "obj2");
      map.put(3, "obj3");
      map.put(4, "obj4");
      map.put(5, "obj5");
      map.put(6, "obj6");
      if (map.size() != 6) {
         return false;//return false if size is not correct 
      }
      if (!map.containsKey(6)) {
         return false;//return false if capacity is not extended 
      }
      else {return true;}
   }
   
   /**
    * this method tests the get method
    * @param map
    * @return true if get the correct value
    */
   public static boolean test4(HashTableMap<Integer, String> map) {
      map.put(1, "obj1");
      map.put(2, "obj2");
      if (!map.get(1).equals("obj1")) {
         return false;
      }
      if (!map.get(2).equals("obj2")) {
         return false;
      }
      else {return true;}
   }
   
   /**
    * this method tests the remove method
    * @param map
    * @return true if hash is removed correctly
    */
   public static boolean test5(HashTableMap<Integer, String> map) {
      map.put(1, "obj1");
      map.put(2, "obj2");
      map.put(3, "obj3");
      Object value = map.remove(2);
      if (map.containsKey(2)) {
         return false;
      }
      else if (value != "obj2") {//test the return value of remove method
         return false;
      }
      else {return true;}
   }
   
   /**
    * this method tests the containsKey method
    * @param map
    * @return true if detects the keys correctly
    */
   public static boolean test6(HashTableMap<Integer, String> map) {
      map.put(1, "obj1");
      map.put(2, "obj2");
      map.put(3, "obj3");
      if (!map.containsKey(2)) {
         return false;//return false if it doesn't detect key 2 
      }
      map.remove(2);//remove key 2
      if (map.containsKey(2)) {//return false if it detects key 2 
         return false;
      }
      if (map.containsKey(null)) {
         return false;
      }
      else {return true;}
   }
   
   /**
    * this method tests the clear method
    * @param map
    * @return
    */
   public static boolean test7(HashTableMap<Integer, String> map) {
      map.put(1, "obj1");
      map.put(2, "obj2");
      map.put(3, "obj3");
      map.clear();
      if (map.containsKey(1) || map.containsKey(2) || map.containsKey(3)) {
         return false;//return false if any of the hash still exists in the array
      }
      else {return true;}
   }
   
   
   
   public static void main(String[] args) {
      HashTableMap<String, String> test1 = new HashTableMap<String, String>();
      System.out.println(test1(test1));
      HashTableMap<Integer, String> test2 = new HashTableMap<Integer, String>(4);
      System.out.println(test2(test2));
      HashTableMap<Integer, String> test3 = new HashTableMap<Integer, String>(5);
      System.out.println(test3(test3));
      HashTableMap<Integer, String> test4 = new HashTableMap<Integer, String>();
      System.out.println(test4(test4));
      HashTableMap<Integer, String> test5 = new HashTableMap<Integer, String>();
      System.out.println(test5(test5));
      HashTableMap<Integer, String> test6 = new HashTableMap<Integer, String>();
      System.out.println(test6(test6));
      HashTableMap<Integer, String> test7 = new HashTableMap<Integer, String>();
      System.out.println(test6(test7));
//      HashTableMap<?, ?> test = new HashTableMap<Object, Object>();
//      test.put(1, "obj1");
//      test.put(2, "obj2");
//      test.put(3, "obj3");
//      System.out.println(test.get(3));
//      test.remove(2);
//      try {
//         System.out.println(test.get(2));
//      }catch(NoSuchElementException e) {
//         System.out.println("Exception caught");
//      }
//      
//      HashTableMap test2 = new HashTableMap(10);
//      test2.put(12, "obj1");
//      test2.put(13, "obj2");
//      System.out.println(test2.put(23, "obj3"));
      
      
      
      
   }

}

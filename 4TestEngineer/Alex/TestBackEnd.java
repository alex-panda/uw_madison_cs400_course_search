// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yulan BAO
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * A TestSuite to test the BackEnd of the application.
 * */
public class TestBackEnd {

   /**
    * The main method used to run the tests.
    */
    public static void main(String[] args) {
        System.out.print("\nTest1: " + "\n");
        System.out.print("    " + test1() + "\n\n");

        System.out.print("Test2: " + "\n");
        System.out.print("   " + test2() + "\n\n");

        System.out.print("Test3: " + "\n");
        System.out.print("   " + test3() + "\n\n");

        System.out.print("Test4: " + "\n");
        System.out.print("   " + test4() + "\n\n");

        System.out.print("Test5: " + "\n");
        System.out.print("   " + test5() + "\n\n");
    }

   /**
    * Test the Hashmap. This test is a compilation of all the tests done by
    *   Alexander Peseckis for his hashmap.
    * */
    public static boolean test1() {
        // Test the hashmap to make sure that it is constructed properly
        {
            HashTableMap<String,String> hashMap = new HashTableMap<String,String>(100);

            // Make sure that the size is 0 when constructed
            if (hashMap.size() != 0) {
                System.out.print("Failed: The hashMap size was supposed to be 0 but was "
                        + hashMap.size() + "\n");
                return false;
            }

            // Add values to the hashMap
            for (int i = 0; i < 100; i++) {
                hashMap.put("Map" + i, "i: " + i);
            }

            // Make sure that the size is the number of key:value pairs that were added to the hashmap.
            if (hashMap.size() != 100) {
                System.out.print("Failed: The hashMap size was supposed to be 100 but was "
                        + hashMap.size() + "\n");
                return false;
            }
        }
        {
            HashTableMap<String,String> hashMap = new HashTableMap<String,String>();
            // Add values to the hashMap
            for (int i = 0; i < 100; i++) {
                hashMap.put("Map" + i, "i: " + i);
            }

            // clear the hashmap
            hashMap.clear();

            // make sure the size of the hash map is now 0
            if (hashMap.size() != 0) {
                System.out.print("Failed: The hashMap size was supposed to be 0 after it was cleared, " +
                        "but was " + hashMap.size() + "\n");
                return false;
            }
        }
        {
            HashTableMap<String,String> hashMap = new HashTableMap<String,String>(10);

            // Add the values to the hashMap
            for (int i = 0; i < 1000; i++) {
                hashMap.put("Map" + i, "i: " + i);
            }

            // test every key that is supposed to have a value and make sure that the
            //      hashmap returns the correct value with .get method
            for (int i = 0; i < 1000; i++) {
                try {
                    String value = hashMap.get("Map" + i);
                    if (value != ("i: " + i)) {
                        System.out.print("Failed: The hashmap did not return the correct value for " +
                                "" + "Map" + i +". Returned value: " + value + "\n");
                        return false;
                    }
                } catch (java.util.NoSuchElementException e) {
                    System.out.print("Failed: The hashmap returned a NoSuchElementException for a key " +
                            "it was supposed to have. Key: " + ("Map" + i) + "\n");
                    return false;
                }
            }

            // Make sure that the hashmap throws a NoSuchElementException for the
            //      keys it does not have
            try {
                hashMap.get("Map" + "1000000000000");
                System.out.print("Failed: The hashmap did not return a NoSuchElementException for a key " +
                        "it is not supposed to have. Key: " + ("Map" + "1000000000000"));
                return false;
            } catch (java.util.NoSuchElementException e) {
            }

            // Makes sure that the .get method does not change the size of the hashmap.
            if (hashMap.size() != 1000) {
                System.out.print("Failed: The hashMap size was supposed to be 1000, " +
                        "but was " + hashMap.size() + "\n");
                return false;
            }
        }
        {
            HashTableMap<String,String> hashMap = new HashTableMap<String,String>();
            // Add the values to the hashMap
            for (int i = 0; i < 1000; i++) {
                hashMap.put("Map" + i, "i: " + i);
            }

            // make sure that the remove method removes and returns the correct value
            //      for each key
            for (int i = 0; i < 980; i++) {
                String value = hashMap.remove("Map" + i);
                if (value != ("i: " + i)) {
                    System.out.print("Failed: The hashmap did not return the correct value for " +
                            "" + "Map" + i +". Returned value: " + value + "\n");
                    return false;
                }
            }

            // Make sure that the remove function reduces the size of the hashmap correctly
            if (hashMap.size() != 20) {
                System.out.print("Failed: The hashMap size was supposed to be 20, " +
                        "but was " + hashMap.size());
                return false;
            }
        }
        {
            HashTableMap<String,String> hashMap = new HashTableMap<String,String>();
            // Add the values to the hashMap
            for (int i = 0; i < 1000; i++) {
                hashMap.put("Map" + i, "i: " + i);
            }

            // Makes sure that the containsKey method return true for every key the hashmap
            //      is supposed to have
            for (int i = 0; i < 1000; i++) {
                if (!hashMap.containsKey("Map" + i)) {
                    System.out.print("Failed: The hashmap.containsKey(key) function returned false" +
                            "for key " + "Map" + i + " when it is supposed to have it." + "\n");
                    return false;
                }
            }

            // Check to make sure the .containsKey method did not change the size of the hashmap
            if (hashMap.size() != 1000) {
                System.out.print("Failed: The hashmap.containsKey(key) function some how changed " +
                        "the size of the array when it was not supposed to. Size is supposed " +
                        "to be 1000, but was " + hashMap.size() + "\n");
                return false;
            }
        }
        return true;
    }

   /**
    */
    public static boolean test2() {
        return true;
    }

   /**
    */
    public static boolean test3() {
        return true;
    }

   /**
    */
    public static boolean test4() {
        return true;
    }

   /**
    */
    public static boolean test5() {
        return true;
    }
}















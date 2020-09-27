// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * A TestSuite to test the DataWranglers' classes.
 * */
public class TestData {

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
    }

   /**
    * Tests whether the DataLoader can be constructed
    * */
    public static boolean test1() {
        // Test that the dataLoader can be Initialized.
        try {
            dataLoader = DataLoader();
        } catch(ExceptionInInitializerError e) {
            println("Failed: The DataLoader class could not be constructed.");
            return false;
        }
        return true;
    }

   /**
    * Test that the Dataloader returns an ArrayList of Courses without any null values in it.
    */
    public static boolean test2() {
        dataLoader = DataLoader();
        // Make sure that the arraylist holds courses
        try {
            ArrayList<Course> courses = dataLoader.getData();
        } catch (Exception e) {
            println("Failed: The DataLoader returned an ArrayList of something other than Courses");
        }

        // Check for null values
        for (int i = 0; i < courses.size(); i++) {
            course = courses[i];
            if (!(course instanceof Course) || course == null) {
                println("Failed: There was a null spot in the array of courses.");
                return false;
            }
        }
        return true;
    }

   /**
    * Test that the Course Class works as it should.
    */
    public static boolean test3() {
        {
            courseName = "CS300";
            ArrayList<Object> prereqs = ArrayList<Object>(10);
            ArrayList<String> noReqs = ArrayList<String>(10);
            Course course = Course(coursName, prereqs, noReqs);

            if (!course.getName().equals(coursName)) {
                println("Failed: The course class does not return the correct course name. " +
                        "Name returned: " + course.getName() + " Expected: " + coursName);
            }
        }
        return true;
    }
}















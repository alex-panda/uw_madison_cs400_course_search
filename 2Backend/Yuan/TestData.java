// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;

/**
 * A TestSuite to test the DataWranglers' classes.
 * */
public class TestData {

   /**
    * The main method used to run the tests.
    */
    public static void main(String[] args) {
        System.out.print("\nTest1 (DataLoader can be constructed): ");
        if (!test1()) {
            System.out.println("NO: FAILED\n");
        } else {
            System.out.println("yes\n");
        }

        System.out.print("Test2 (No null values in DataLoader return): ");
        if (!test2()) {
            System.out.println("NO: FAILED\n");
        } else {
            System.out.println("yes\n");
        }

        System.out.print("Test3 (Course Class returns the same data it was made to store): ");
        if (!test3()) {
            System.out.println("NO: FAILED\n");
        } else {
            System.out.println("yes\n");
        }
    }

   /**
    * Tests whether the DataLoader can be constructed
    * */
    public static boolean test1() {
        // Test that the dataLoader can be Initialized.
        try {
            @SuppressWarnings("unused")
            DataLoader dataLoader = new DataLoader();
        } catch(ExceptionInInitializerError e) {
            System.out.println("Failed: The DataLoader class could not be constructed.");
            return false;
        }
        return true;
    }

   /**
    * Test that the Dataloader returns an ArrayList of Courses without any null values in it.
    */
    public static boolean test2() {
        DataLoader dataLoader = new DataLoader();
        // Make sure that the arraylist holds courses
        ArrayList<Course> courses;
        try {
            courses = dataLoader.getData();
        } catch (Exception e) {
            System.out.println("Failed: The DataLoader returned an ArrayList of something other than Courses");
            return false;
        }

        // Check for null values
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            if (!(course instanceof Course) || course == null) {
                System.out.println("Failed: There was a null spot in the array of courses.");
                return false;
            }
        }
        return true;
    }

   /**
    * Test that the Course Class works as it should.
    */
    public static boolean test3() {
        // See if the class can be constructed
        {
            String courseName = "CS300";
            ArrayList<Object> prereqs = new ArrayList<Object>(10);
            ArrayList<String> noReqs = new ArrayList<String>(10);
            Course course = new Course(courseName, prereqs, noReqs);

            // Test that everything that was put in to the class is also returned correctly
            if (!course.getName().equals(courseName)) {
                System.out.println("Failed: The course class does not return the correct course name. "
                        + "Name returned: " + course.getName() + " Expected: " + courseName);
                return false;
            } else if (!course.getCoursePrereqs().equals(prereqs)) {
                System.out.println("Failed: The course class does not return the correct course prereqs. "
                        + "Prereqs returned: " + course.getCoursePrereqs() + " Expected: " + prereqs);
                return false;
            } else if (!course.getCourseNoreqs().equals(noReqs)) {
                System.out.println("Failed: The course class does not return the correct course noreqs. "
                        + "Noreqs returned: " + course.getCourseNoreqs() + " Expected: " + noReqs);
                return false;
            }
        }
        return true;
    }
}















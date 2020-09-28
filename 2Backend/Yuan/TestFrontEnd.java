// --== CS400 File Header Information ==--
// Name: Alexander Peseckis
// Email: peseckis@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * A TestSuite to test the HashTableMap class.
 * */
public class TestFrontEnd {

   /**
    * The main method used to run the tests.
    */
    public static void main(String[] args) {
        System.out.print("\nTest1 (Manually inspect course outputs):\n");
        test1();
    }

   /**
    * Test that sets up the command line so that the tester can very easily look through and
    *   check whether the classes are displayed correctly (must manually check because
    *   there are many ways to display the data because of multiple frontends).
    */
    public static boolean test1() {
        Scanner inScanner = new Scanner(System.in);
        System.out.println("Visually Inspect Class Returns? (y/n)");
        String answer = inScanner.next();
        ArrayList<String> recordedClasses = new ArrayList<>();
        if (answer.equals("y") || answer.equals("Y")) {
            boolean pick = true;
            int i = 0;
            int upperB = 0;
            String department = "";
            String lowerBounds = "0";
            String upperBounds = "0";
            do {
                try {
                    System.out.println("What is the department you want to test? (ex. CS)");
                    department = inScanner.next();
                    System.out.println("What is the lower bounds of classes you want to test? (ex. 000)");
                    lowerBounds = inScanner.next();
                    System.out.println("What is the upper bounds of classes you want to test? (ex. 999)");
                    upperBounds = inScanner.next();

                    i = Integer.parseInt(lowerBounds);
                    upperB = Integer.parseInt(upperBounds);

                    pick = false;
                } catch (Exception e) {
                    System.out.println("\nPlease input valid answers.\n");
                }
            } while (pick);
            // Get the classes
            UserHashMapInterface classesHashmap = new UserHashMapInterface();

            System.out.println("You can input \"q\" at any time to quit the loop.");
            InputStream sysInBackup = System.in; // Backup of the System.in scanner
            PrintStream sysOutBackup = System.out; // Backup of the System.out scanner

            // The csv with courses in (Course Name, Prerequisites, Norequisites) format
            ArrayList<String[]> courseCSV = readCSVIntoArrayList();

            while (i <= upperB) {
                String userClass = "" + department + "" + i;

                // check if the class is in the csv
                boolean inCSV = false;
                int csvCourseNum = 0;
                for (int k = 0; k < courseCSV.size(); k++) {
                    if (courseCSV.get(k)[0].equals(userClass)) {
                        csvCourseNum = k;
                        inCSV = true;
                    }
                }
                // If the current department and number is a course
                if (inCSV) {
                    // Seprate and move a line down for whitespace for readability
                    System.out.print("\n\n");
                    System.out.println("---------------------------------------------");
                    System.out.println("                  Next Class:");
                    System.out.println("---------------------------------------------");
                    System.out.println("In CSV:");

                    @SuppressWarnings("unused")
                  Course course = classesHashmap.getCourse(userClass);
                    // Print class name
                    System.out.println("Class: " + courseCSV.get(csvCourseNum)[0]);

                    // Print class Prereqs
                    System.out.print("Prereqs: ");
                    System.out.print(courseCSV.get(csvCourseNum)[1]);

                    System.out.print("\n");

                    // print the course's prereqs
                    System.out.print("Noreqs: ");
                    System.out.println(courseCSV.get(csvCourseNum)[2]);
                    // print buffer newline
                    System.out.println("");

                    try {
                        ByteArrayInputStream inBuffStream =
                            new ByteArrayInputStream(("C\n" + userClass + "\nD\n").getBytes());
                        System.setIn(inBuffStream);
                        // Start up the main commandLinedriver
                        CommandLineDriver.main(null);

                        // Return the system.in to normal
                        System.setIn(sysInBackup);

                        boolean askagain = true;
                        boolean escape = false;

                        do {
                            System.out.println("\n\nWas what was displayed correct? (y/n/q):");
                            String nextIn = inScanner.nextLine();

                            if (nextIn.equals("y")) {
                                askagain = false;
                            } else if (nextIn.equals("n")) {
                                System.out.println("Class Recorded.");
                                recordedClasses.add(userClass);
                                askagain = false;
                            } else if (nextIn.equals("q")) {
                                escape = true;
                                break;
                            }
                        } while (askagain);

                        // Exit the loop completely
                        if (escape) {
                            break;
                        }

                    } catch (Exception e) {
                        System.setIn(sysInBackup);
                        System.setOut(sysOutBackup);
                        System.out.print("An Exception occured. Exception: " + e);
                        e.printStackTrace();
                    }
                }
                i++;
            }
        }
        
        inScanner.close();
        // If any classes were marked as failure, then print them and return false.
        if (recordedClasses.size() != 0) {
            System.out.println("Classes that failed: ");
            System.out.println("" + recordedClasses);
            return false;
        }
        return true;
    }

    // Helper Classes
    /**
     * Helper method that reads data from the CS_course_info csv and returns an
     *      ArrayList of String arrays containing what each comma-seperated section contained.
     *
     * @return an ArrayList of String arrays containing each course's course number, prerequisites,
     * and norequisites (the courses that you cannot take if you wish to take this course).
     */
    private static ArrayList<String[]> readCSVIntoArrayList() {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader("CS_course_info.csv"));
            String line = "";
            ArrayList<String[]> courseList = new ArrayList<>();
            csvReader.readLine();
            while ((line = csvReader.readLine()) != null) {
                // use comma as separator
                String[] courseInfo = line.split(",");
                courseList.add(courseInfo);
            }
            return courseList;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (Exception e) {
                }
            }
        }
        return null;
    }
}















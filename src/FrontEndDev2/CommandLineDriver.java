// --== CS400 File Header Information ==--
// Name: Aidan Godfrey
// Email: amgodfrey@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

package FrontEndDev2;
import BackendDeveloper.Course;
import BackendDeveloper.UserHashMapInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Runs the application by loading the hashmap and allowing users to change it
 */
public class CommandLineDriver {
    private static UserHashMapInterface hashTableMap; // One HashMap throughout the whole user experience
    private static Scanner scanner;
    private static boolean quit;

    public CommandLineDriver() {
        this.hashTableMap = new UserHashMapInterface();
    }

    public static void main(String[] args) {
        CommandLineDriver driver = new CommandLineDriver(); // Might have to add while true loops to work correctly
        driver.greeting();
        scanner = new Scanner(System.in);
        while (!quit) {
            String command = scanner.nextLine();
            driver.commandCases(command);
            driver.options();
        }
        driver.goodbye();
        scanner.close();
    }

    private void greeting() {
        System.out.println("---------------------------------------------------");
        System.out.println("-----  Welcome to UW-Madison CS Courses Guide -----");
        System.out.println("   ");
        System.out.println("You can access different courses to see the prerequisites when making your CS schedule.");
        System.out.println("You have the option to add courses us developers have not added yet, delete old courses, " +
                "and lookup courses you are interested in taking");
        System.out.println("");
        this.options();
    }

    private void options() {
        System.out.println("");
        System.out.println("Below are the following options: ");
        System.out.println("      [a]: Press [a] to add a course to the selection of CS courses");
        System.out.println("      [l]: Press [l] to lookup a course from the selection of CS courses and see prerequistes");
        System.out.println("      [r]: Press [r] to remove a course from the selection of CS courses");
        System.out.println("      [q]: Press [q] to quit the application");
        System.out.println("Choose one and press enter: ");

    }


    private void commandCases(String command) {
        if (command.equals("a")) {
            this.addCourse();
        } else if (command.equals("l")) {
            this.lookUpCourse();
        } else if (command.equals("r")) {
            this.removeCourse();
        } else if (command.equals("q")) {
            this.quit = true;
            this.goodbye();
        } else {
            System.out.println("Try a valid command!");
        }
    }

    private void addCourse() {
        System.out.println("Title of Course (i.e. CS400 or CS###): ");
        String title = scanner.nextLine();
        System.out.println("Title of Preqequistes (i.e. CS400 CS500 CS###): ");

        String preq = scanner.nextLine(); //Maybe have to add a while true for it to work
        ArrayList<Object> preqequistes = new ArrayList<Object>();

        if (!preq.contains(" ")) {
            preqequistes.add(preq);
        } else {
            String[] preqsArray = preq.split(" ");
            Collections.addAll(preqequistes, preqsArray);
        }
        Course newCourse = new Course(title, preqequistes, null);
        this.hashTableMap.addCourse(newCourse);

        System.out.println("The Course " + newCourse.getName() + " has been added!");
    }

    private void removeCourse() {
        System.out.println("What course would you like to be removed? (i.e. CS400 or CS###): ");
        String title = scanner.nextLine();
        if (hashTableMap.removeCourse(title)) {
            System.out.println("The class has been removed successfully!");
        } else {
            System.out.println("Uh oh... The class has not been removed successfully! " +
                    "Try again and make sure it exsits!");
        }
    }

    private void lookUpCourse() {
        System.out.println("What course are you looking to see the preqequistes for?(i.e CS400 or CS###): ");
        String title = scanner.nextLine();
        Course course = hashTableMap.getCourse(title);
        if (course == null) {
            System.out.println("The course could not be found, try again!");
        } else {
            System.out.println(this.recursivePrereqs(course, ""));
        }
    }

    private String recursivePrereqs(Course course, String fullString) {
        fullString += "The prequistes for " + course.getName() + " are: ";
        fullString += "\n";
        if (course.getPrereqs() == null) {
            fullString += "\n ----No prerequistes";
            return fullString;
        }

        String combined = "";
        ArrayList<Object> prereqs = course.getPrereqs();
        for (Object prereq : prereqs) {
            if(combined.equals("")){
                combined = prereq + ", ";
            }
            else{
                combined = combined + prereq + ", ";
            }
        }
        fullString += "\n ----" + combined;

        for (Object prereq : prereqs) {
            Course preqCourse = hashTableMap.getCourse(prereq.toString());
            if (preqCourse == null) {
                continue;
            } else {
                return fullString += "--------" + this.recursivePrereqs(preqCourse, fullString);
            }
        }
        return fullString;
    }


    private void goodbye(){
        System.out.println("---------------------------------------------------");
        System.out.println("----         Goodbye, Have a Nice Day!         ----");
    }


}
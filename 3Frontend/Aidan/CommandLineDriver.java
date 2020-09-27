// --== CS400 File Header Information ==--
// Name: Aidan Godfrey
// Email: amgodfrey@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Florian Heimerel
// Notes to Grader: We never fully agreed on the format of file structures, I suggested a source file with packages but
// CONTIUED: my commit was reverted so the packages will not be correct with my specific imports

package FrontEndDeveloper2;
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
    private static Scanner scanner; // Takes the user input
    private static boolean quit; // Checks everytime a input is done if it wants to quit

    public CommandLineDriver() {
        this.hashTableMap = new UserHashMapInterface();
    }


    /**
     * Runs the application
     * @param args not used
     */
    public static void main(String[] args) {
        CommandLineDriver driver = new CommandLineDriver();
        driver.greeting();
        scanner = new Scanner(System.in);

        // Main loop that runs the application and quits when a q is inputed
        while (!quit) {
            String command = scanner.nextLine(); // Check the command
            driver.commandCases(command);        // Reroute the command
            driver.options();                    // Print out the options once done
        }
        driver.goodbye();
        scanner.close();
    }

    /**
     * Prints to the console a welcome message
     */
    private void greeting() {
        System.out.println("---------------------------------------------------");
        System.out.println("----    Welcome to UW-Madison CS Courses Guide ----");
        System.out.println("   ");
        System.out.println("You can access different courses to see the prerequisites when making your CS schedule.");
        System.out.println("You have the option to add courses us developers have not added yet, delete old courses, " +
                "and lookup courses you are interested in taking");
        System.out.println("");
        this.options();
    }

    /**
     * Prints the variety of options for the user to check
     */
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
        if (command.equals("a")) { // Add a Course
            this.addCourse();
        } else if (command.equals("l")) { // Lookup a Course
            this.lookUpCourse();
        } else if (command.equals("r")) { // Remove a Course
            this.removeCourse();
        } else if (command.equals("q")) { // Quit from the application
            this.quit = true;
            this.goodbye();
        } else {
            System.out.println("Try a valid command!");
        }
    }

    /**
     * Add a courses by taking in user inputs: title of course, and the perquisites
     */
    private void addCourse() {
        System.out.println("Title of Course (i.e. CS400 or CS###): ");
        String title = scanner.nextLine();

        // If the title is in out table, we cannot add it
        if(hashTableMap.getCourse(title) != null){
            System.out.println("The course already exists! Try Again!");
            return;
        }

        System.out.println("Title of Preqequistes (i.e. CS400 CS500 CS###): ");

        // Preq comes in as Courses separated by spaces, must separate by spaces into a ArrayList
        String preq = scanner.nextLine();
        ArrayList<String> preqequistes = new ArrayList<String>();

        if (!preq.contains(" ")) {
            preqequistes.add(preq);
        } else {
            String[] preqsArray = preq.split(" ");
            Collections.addAll(preqequistes, preqsArray);
        }

        // Ensure the course names are valid
        for(String preqe: preqequistes){
            if(!preqe.contains("CS")){
                System.out.println("Not a valid course name! Try Again!");
                return;
            }
        }

        Course newCourse = new Course(title, preqequistes, null);
        if(this.hashTableMap.addCourse(newCourse)) {
            System.out.println("The Course " + newCourse.getName() + " has been added!");
        }
        else{
            System.out.println("The Course " + newCourse.getName() + " has NOT been added. Try Again!");

        }
    }


    /**
     * Remove the course if possible given the title of the course
     */
    private void removeCourse() {
        System.out.println("What course would you like to be removed? (i.e. CS400 or CS###): ");
        String title = scanner.nextLine();

        if (hashTableMap.removeCourse(title)) {
            System.out.println("The class has been removed successfully!");
        }
        else {
            System.out.println("Uh oh... The class has not been removed successfully! " +
                    "Try again and make sure it exists!");
            System.out.println("");
        }
    }

    /**
     * Lookup the course and ensure it is found, if yes: print out the prereqs
     */
    private void lookUpCourse() {
        System.out.println("What course are you looking to see the preqequistes for?(i.e CS400 or CS###): ");
        String title = scanner.nextLine();
        Course course = hashTableMap.getCourse(title);
        if (course == null) {
            System.out.println("The course could not be found, try again!");
        } else {
            System.out.println(this.recursivePrereqs(course, "", 0, new ArrayList<String>()));
        }
    }

    private String recursivePrereqs(Course course, String fullString, int depth, ArrayList<String> previousPrinted) {

        // String the indicates what level is needed in order to take the class
        String depthString = "";
        for(int i=0; i< depth; i++){
            depthString += "-------";
        }

        // Find the course title
        fullString += depthString + "The prequistes for " + course.getName() + " are: ";
        previousPrinted.add(course.getName());

        // If no requirements, then just print the first statement
        if (course.getPrereqs() == null) {
            fullString += depthString + "\n ----No prerequistes";
            return fullString;
        }

        // Combine the prerequisites underneath the course title
        String combined = "";
        ArrayList<String> prereqs = course.getPrereqs();
        for (String prereq : prereqs) {
            if(combined.equals("")){
                combined = prereq + ", ";
            }
            else{
                combined = combined + prereq + ", ";
            }
        }

        // Remove the last comma
        int lastComma = combined.lastIndexOf(",");
        combined = combined.substring(0, lastComma);
        fullString += "\n" + depthString + "     " + combined;

        // Recursively print out the next course
        for (String prereq : prereqs) {
            Course preqCourse = hashTableMap.getCourse(prereq.strip());

            // If the course is null, or if it is the course is a prequisite of itself, ignore
            if (preqCourse == null || preqCourse.getName().equals(course.getName())) {
                continue;
            }
            else {
                // Dont print a course twice
                if(previousPrinted.contains(preqCourse.getName())){
                    return "";
                }
                return fullString += "\n \n"  + this.recursivePrereqs(preqCourse, "", depth +1, previousPrinted);
            }
        }
        return fullString;
    }


    /**
     * Goodbye print statement when the application is quit
     */
    private void goodbye(){
        System.out.println("---------------------------------------------------");
        System.out.println("----         Goodbye, Have a Nice Day!         ----");
    }


}

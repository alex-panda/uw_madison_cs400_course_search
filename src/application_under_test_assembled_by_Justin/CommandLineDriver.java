// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// Role: Front End Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: This is the driver of our application

import BackendDeveloper.UserHashMapInterface;
import BackendDeveloper.Course;

import java.util.Scanner;

/**
 * This class is the driver of the UW-Madison CS Course Planning Tool application. It allows users
 * to search: [A] whether a CS course is offered at UW-Madison, [B] the direct prerequisites of a CS
 * course, [C] all prerequisites of a CS course. Command [D] will exist the application. The
 * commands uses input are not case sensitive, but the course name inputs have to follow this format
 * in order to search precisely: "CSXXX" (such as CS400). The application will also notify the user
 * that they can't take a course being searched if they've taken some courses if any.
 *
 * @author Justin Qiao
 */
public class CommandLineDriver {

  // the prompts that are going to display to users
  private final static String WELCOME_MESSAGE =
      "======= WELCOME to the UW-Madison CS Course Planning Tool! =======\n"
          + "This application is limited to search for courses under the \n"
          + "Computer Science department.\n";
  private final static String BYE_MESSAGE =
      "============= Thank you for using this Application! ==============";
  private final static String MENU =
      "\nCOMMAND MENU:\n" + "[A] Check whether a CS course is offered at UW-Madison.\n"
          + "[B] Check the direct pre-requisites of a CS course being offered.\n"
          + "[C] Check all course needed for taking a high level CS course.\n" + "[D] Exit.\n"
          + "Please enter your command: ";
  private final static String INVALID_COMMAND =
      "\nSorry, the command you just entered was invalid.\n"
          + "please pick a letter from A to D for its corresponding command.\n";
  private final static String NEXT_COMMAND =
      "\n=================================================================="
          + "\nIs there anything else we can help?(Please choose a command)";

  // store the instance of this application for its private methods
  private static UserHashMapInterface application;

  /**
   * Helper method to print the indentation in the formatted tree output.
   * 
   * @param indent - the number of how many indentation need (1 means "----")
   */
  private static void printIndent(int indent) {
    for (int i = 0; i < indent; i++)
      System.out.print("----");
  }

  /**
   * Helper method to print the courses user cannot take if they take the given course if any.
   * 
   * @param courseName - the given course under check
   */
  private static void printNoRereqs(String courseName) {
    Course course = application.getCourse(courseName);
    if (course.hasNoreqs()) {
      System.out.println(
          "\nNote: you cannot take " + courseName + " if you've taken any of these courses:");
      System.out.println(course.getNoreqs().toString());
    }
  }

  /**
   * This method print all of the prerequisites(deep to the most basic courses with no prerequisite)
   * in a tree structure. It implement this by recursively calling itself. This method will find
   * prerequisites of a prerequisite that is under the CS department ONLY. For instance, if a course
   * require CS 400 and Math 234, only the deeper prerequisites of CS 400 will be searched.
   * 
   * @param course - the current course we are trying to query its prerequisites
   * @param indent - the number of how many indentation need (1 means "----")
   */
  private static void printTree(Course course, int indent) {
    if (course.hasPrereqs()) {
      Object[] prereqs = course.getPrereqs().toArray();
      for (int i = 0; i < prereqs.length; i++) {
        // store and print the current course's direct prerequisites
        String temp = prereqs[i].toString();
        printIndent(indent);
        System.out.println(temp);
        // find all deeper prerequisites recursively
        for (int j = 0; j < temp.length(); j++)
          if (temp.charAt(j) == 'C') { // ONLY CS courses are interested when going deeper
            String deeper = temp.substring(j, j + 5);
            if (application.getCourse(deeper) != null) {
              Course deeperCourse = application.getCourse(deeper);
              // do the deeper search only when a prerequisite course have more deeper prerequisites
              if (deeperCourse.hasPrereqs()) {
                System.out.println();
                printIndent(indent + 1);
                System.out.println("To take " + deeper + ", you need to take:");
                printTree(deeperCourse, indent + 1);
              }
              printNoRereqs(deeper);
            }
          }
      }
    }
  }

  /**
   * The main method drives the application.
   * 
   * Example Course Input: CS514
   * 
   * Example Outputs:
   * 
   * ----Function[A]: CS514 is offered at UW-Madison!
   * 
   * ----Function[B]: The direct prerequisites of CS514 are: [MATH320, MATH340, MATH341, MATH375]
   * [MATH322, MATH376, MATH421, MATH521] [CS200, CS220, CS300, CS310, CS301]
   *
   * Note: you only need to take one course from each row!
   * 
   * ----Function[C]:
   * 
   * The prerequisites of CS514 are: [MATH320, MATH340, MATH341, MATH375] [MATH322, MATH376,
   * MATH421, MATH521] [CS200, CS220, CS300, CS310, CS301]
   * 
   * ----To take CS300, you need to take: ----[CS200, CS220, CS302, CS310, CS301]
   * 
   * --------To take CS310, you need to take: --------MATH222
   * 
   * ----To take CS310, you need to take: ----MATH222
   * 
   * Note: you only need to take one course from each row!
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    // create a new instance of the application, this initialize the hashTabke with the Courses
    application = new UserHashMapInterface();
    System.out.print(WELCOME_MESSAGE);
    Scanner scanner = new Scanner(System.in);
    // this while loop only end when customer request for exit
    while (true) {
      char command;
      // this while loop ask customer for a command until it's valid
      while (true) {
        System.out.print(MENU);
        String word = scanner.next();
        if (word.length() == 1) {
          command = word.charAt(0);
          // make the command input case insensitive
          if (Character.isLowerCase(command))
            command = Character.toUpperCase(command);
          if ('A' <= command && command <= 'D')
            break;
        } else
          System.out.print(INVALID_COMMAND);
      }
      // exit the application
      if (command == 'D') {
        System.out.print(BYE_MESSAGE);
        break;
      } else {
        System.out.print("Please Type in a course name(e.g. CS400): ");
        String courseName = scanner.next(); // the course name being queried
        // tell customer whether the course is offered(valid)
        if (command == 'A') {
          if (application.getCourse(courseName) != null)
            System.out.println("\n" + courseName + " is offered at UW-Madison!");
          else
            System.out.println("\nSorry, " + courseName + " is not offered at UW-Madison.");
        }
        // return the prerequisites(ONLY the value attached to the key)
        if (command == 'B') {
          if (application.getCourse(courseName) != null) {
            // get the current course interested if valid
            Course course = application.getCourse(courseName);
            // print its direct prerequisites if any
            if (course.hasPrereqs()) {
              Object[] prereqs = course.getPrereqs().toArray();
              System.out.print("\nThe direct prerequisites of " + courseName + " are: \n");
              for (int i = 0; i < prereqs.length; i++)
                System.out.println(prereqs[i].toString() + " ");
              System.out.println();
              System.out.println("Note: you only need to take one course from each row!");
            } else // otherwise tells the user that there is no prerequisite
              System.out.print("\nThere is no prerequisite for " + courseName + ".\n");
            printNoRereqs(courseName);
          } else // otherwise tells the user that the input was invalid
            System.out.println("\nSorry, " + courseName + " is not offered at UW-Madison.");
        }
        // return the whole tree to customer (with pre-prerequisites)
        if (command == 'C') {
          if (application.getCourse(courseName) != null) {
            // get the current course interested if valid
            Course course = application.getCourse(courseName);
            // find all of its prerequisites if any
            if (course.hasPrereqs()) {
              System.out.print("\nThe prerequisites of " + courseName + " are: \n");
              // calling the recursive method to build the output tree
              printTree(course, 0);
              System.out.println();
              System.out.println("Note: you only need to take one course from each row!");
            } else // otherwise tells the user that there is no prerequisite
              System.out.print("\nThere is no prerequisite for " + courseName + ".\n");
          } else // otherwise tells the user that the input was invalid
            System.out.println("\nSorry, " + courseName + " is not offered at UW-Madison.");
        }
        System.out.print(NEXT_COMMAND); // ask for more commands until exit
      }
    }
    scanner.close();
  }

}

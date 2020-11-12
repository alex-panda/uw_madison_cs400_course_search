// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// Role: Front End Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: This is the driver of our application

import java.util.List;
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
      System.out.print("    ");
  }

  /**
   * Helper method to print the courses user cannot take if they take the given course if any.
   *
   * @param course - the given course under check
   */
  private static void printNoRereqs(String courseName) {
    Course course = application.getCourse(courseName);
    if (course.hasNoreqs()) {
      System.out
          .print("Note: you cannot take " + courseName + " if you've taken any of these courses: ");
      System.out.println(course.getCourseNoreqs());
      System.out.println();
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
      // the Objects can be Lists or Strings
      Object[] prereqs = course.getCoursePrereqs().toArray();
      for (int i = 0; i < prereqs.length; i++) { // reads through the current course's prerequisites
        if (prereqs[i] instanceof List<?>) { // if an Object is a List
          List<String> arrayList = (List<String>) prereqs[i]; // cast it back to List of Strings
          for (int j = 0; j < arrayList.size(); j++) {
            // print one prerequisite at a time
            printIndent(indent);
            if (j == 0)
              System.out.println(arrayList.get(j));
            else
              System.out.println("or " + arrayList.get(j));
            // find pre-prerequisites if any
            String deeper = arrayList.get(j);
            if (application.getCourse(deeper) != null) {
              Course deeperCourse = application.getCourse(deeper);
              // do the deeper search only when a prerequisite course have deeper prerequisites
              if (deeperCourse.hasPrereqs()) {
                printIndent(indent);
                printTree(deeperCourse, indent + 1);
              }
            }
          }
          System.out.println();
        } else if (prereqs[i] instanceof String) { // if an Object is a String
          // print it with correct format
          printIndent(indent);
          System.out.println(prereqs[i]);
          // search deeper if there are any pre-prerequisites
          if (application.getCourse((String) prereqs[i]) != null) {
            Course deeperCourse = application.getCourse((String) prereqs[i]);
            if (deeperCourse.hasPrereqs()) {
              printIndent(indent);
              printTree(deeperCourse, indent + 1);
            }
          }
        }
      }
    }
    printNoRereqs(course.getName()); // print any note on restrictions
  }

  /**
   * The main method drives the application. It's functionality is described in the class header.
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
              Object[] prereqs = course.getCoursePrereqs().toArray();
              System.out.print("\nThe direct prerequisites of " + courseName + " are: \n");
              // read through the prerequisites to format the output
              for (int i = 0; i < prereqs.length; i++) {
                if (prereqs[i] instanceof List<?>) {
                  List<String> arrayList = (List<String>) prereqs[i];
                  // the first term of any or relationship would not have an "or" before it
                  System.out.println(arrayList.get(0));
                  // while the later term have the "or" added
                  for (int j = 1; j < arrayList.size(); j++)
                    System.out.println("or " + arrayList.get(j));
                  System.out.println();
                } else // if there is only one prerequisites, just print it
                  System.out.println(prereqs[i]);
              }
            } else // otherwise tells the user that there is no prerequisite
              System.out.print("\nThere is no prerequisite for " + courseName + ".\n");
            printNoRereqs(courseName);  // print any note on restrictions
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

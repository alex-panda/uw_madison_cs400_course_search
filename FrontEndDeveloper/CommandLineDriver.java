// --== CS400 File Header Information ==--
// Name: Justin Qiao
// Email: sqiao6@wisc.edu
// Team: DC
// Role: Front End Developer
// TA: Yelun Bao
// Lecturer: Florian Heimerl
// Notes to Grader: This is the driver of our application

import java.util.Scanner;

public class CommandLineDriver {

  private final static String WELCOME_MESSAGE =
      "======== WELCOME to the UW-Madison Course Planning Tool! ========\n"
          + "This prototype of this application is limited to search for courses \n"
          + "under the Computer Science department.\n";
  private final static String BYE_MESSAGE =
      "============= Thank you for using this Application! =============";
  private final static String MENU =
      "\nCOMMAND MENU:\n" + "[A] Check whether a CS course is offered at UW-Madison.\n"
          + "[B] Check the direct pre-requisites of a CS course being offered.\n"
          + "[C] Check all course needed for taking a high level CS course.\n" + "[D] Exit.\n"
          + "Please enter your command: ";
  private final static String INVALID_COMMAND =
      "\nSorry, the command you just entered was invalid.\n"
          + "please pick a letter from A to D for its corresponding command.\n";
  private final static String NEXT_COMMAND =
      "\nIs there anything else we can help?(Please choose a command)";

  // This method print all of the prereqs in a tree structure for customer
  // !!!!!!!!!! NOT FINISHED YET
  private static void printTree(Course course) {
    if(course.hasPrereqs()) {
      Course[] prereqs = course.getCoursePrereqs().toArray();
      for(int i = 0; i < prereqs.length; i++) {
        if(prereqs[i].hasPrereqs())
          printTree(prereqs[i]);
        System.out.println(prereqs[i].getName());
        System.out.println();}
    }
    else
      System.out.println(course.getName());
  }
  
  public static void main(String[] args) {
    UserHashMapInterface application = new UserHashMapInterface(); // create a new instance of the
                                                                   // application, this initialize
                                                                   // the hashTabke with the
                                                                   // Courses
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
        // return the prereqs(ONLY the value attached to the key)
        if (command == 'B') {
          if (application.getCourse(courseName) != null) {
            Course course = application.getCourse(courseName);
            if(course.hasPrereqs()) {
              Course[] prereqs = course.getCoursePrereqs().toArray();
              System.out.print("\nThe direct prerequisites of " + courseName + " are: ");
              for(int i = 0; i < prereqs.length; i++)
                System.out.print(prereqs[i].getName() + " ");
              System.out.println();
            }
            else
              System.out.print("\nThere is no prerequisite for " + courseName + ".\n");
          }
          else
            System.out.println("\nSorry, " + courseName + " is not offered at UW-Madison.");
        }
        // return the whole tree to customer (with pre-pre-reqs)
        if (command == 'C') {
          if (application.getCourse(courseName) != null) {
            Course course = application.getCourse(courseName);
            if(course.hasPrereqs()) {
              System.out.print("\nThe prerequisites of " + courseName + " are: \n");
              printTree(course);
              System.out.println();
            }
            else
              System.out.print("\nThere is no prerequisite for " + courseName + ".\n");
          }
          else
            System.out.println("\nSorry, " + courseName + " is not offered at UW-Madison.");
        }
        System.out.print(NEXT_COMMAND); // ask for more commands until exit
      }
    }
    scanner.close();

  }

}

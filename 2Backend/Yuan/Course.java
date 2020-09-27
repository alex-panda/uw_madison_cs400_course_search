// --== CS400 File Header Information ==--
// Name: Xiaohan Shen
// Email: xshen97@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None
import java.util.ArrayList;

/**
 * Represents a course, stores information about the course's prerequisites and the courses that cannot be taken
 * before taking this course.
 *
 * @author Xiaohan Shen <xshen97@wisc.edu> on 09/25/2020
 */
public class Course {
    private String name;
    // ¡°(CS 200|CS300)&(CS400)¡± = [CS400,[CS300,CS200]]
    private ArrayList<Object> prereqs;

    private ArrayList<String> noreqs;

    /**
     * Constructs a Course object with specified course number, prerequisites, and courses that cannot be taken
     * prior to this course.
     *
     * @param name is the course number, such as "CS400".
     * @param prereqs is the prerequisites of the course.
     * @param noreqs is courses that cannot be taken prior to the course.
     */
    public Course(String name, ArrayList<Object> prereqs, ArrayList<String> noreqs) {
        this.name = name;
        this.prereqs = prereqs;
        this.noreqs = noreqs;
    }

    /**
     * Constructs a Course object with specified course number and prerequisites, and assumes that there is
     * no restriction on courses that cannot be taken prior to this course.
     *
     * @param name is the course number, such as "CS400".
     * @param prereqs is the prerequisites of the course.
     */
    public Course(String name, ArrayList<Object> prereqs) {
        this(name, prereqs, null);
    }

    /**
     * Returns the course number.
     *
     * @return the course number.
     */
    public String getName() {
        return name;
    };

    /**
     * Returns whether the course has prerequisites.
     *
     * @return true if the course has prerequisites, false otherwise.
     */
    public Boolean hasPrereqs() {
        //return (prereqs == null);
       return prereqs.size() != 0;
    }

    /**
     * Returns whether the course has courses that cannot be taken prior to this course.
     *
     * @return true if the course has courses that cannot be taken prior to this course, false otherwise.
     */
    public Boolean hasNoreqs() {
        //return (noreqs == null);
       return noreqs.size() != 0;
    }

    /**
     * Returns the list of course prerequisites.
     *
     * @return the list of course prerequisites.
     */
    public ArrayList<Object> getCoursePrereqs() {
        return prereqs;
    }

    /**
     * Returns the list of courses that cannot be taken prior to this course.
     *
     * @return the list of courses that cannot be taken prior to this course.
     */
    public ArrayList<String> getCourseNoreqs() {
        return noreqs;
    }
}
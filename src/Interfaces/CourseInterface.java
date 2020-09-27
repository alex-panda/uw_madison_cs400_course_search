package Interfaces;

import java.util.ArrayList;

/**
 * A public interface to define what Courses must look like.
 * */
public interface CourseInterface {
    public String getName();
    public ArrayList<String> getPrereqs();
    public Arraylist<String> getNoreqs();
    public boolean hasPrereqs();
    public boolean hasNoreqs();
}

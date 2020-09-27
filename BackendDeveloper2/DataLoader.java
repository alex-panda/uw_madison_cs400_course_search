// --== CS400 File Header Information ==--
// Name: Xiaohan Shen
// Email: xshen97@wisc.edu
// Team: DC
// TA: Yelun
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Loads course information including courses' prerequisites and courses that cannot be taken prior to taking certain
 * courses from target csv file.
 */
public class DataLoader{
    private ArrayList<Course> data;
    private String csvPath;

    /**
     * Constructs a DataLoader object that reads course data from csv file at specified path.
     * This constructor is intended for testing purposes since one can choose to load from test csv file.
     *
     * @param csvPath is the path of the target csv file
     */
    public DataLoader(String csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Constructs a DataLoader that reads course data from default csv file.
     */
    public DataLoader() {
        csvPath = "CS_course_info.csv";
    }

    /**
     * Returns the course data read from target csv file.
     *
     * @return an ArrayList of Course objects that includes all course data from target csv file.
     */
    public ArrayList<Course> getData() {
        ArrayList<String[]> courseList = readData();
        //ArrayList<Course> courses = new ArrayList<>();
        data = new ArrayList<>();
        for (String[] courseInfo: courseList) {
            String courseName = courseInfo[0];
            ArrayList<Object> prereqs = (courseInfo[1] == "None") ? null : parsePrereqs(courseInfo[1]);
            //ArrayList<String> noreqs = (courseInfo[2] == "None") ? null :
            //         Arrays.asList(courseInfo[2].split("&"));
            //courses.add(new Course(courseName, prereqs, noreqs));
            ArrayList<String> noreqs = new ArrayList<String>();
            noreqs.addAll((courseInfo[2] == "None") ? null : Arrays.asList(courseInfo[2].split("&")));
            data.add(new Course(courseName, prereqs, noreqs));
        }
        //return courses;
        return data;
    }

    /**
     * Helper method that parses the prerequisites stored in csv file.
     *
     * @param prereqs is the String of prerequisites in csv file.
     * @return the prerequisites represented in prereqs.
     */
    private ArrayList<Object> parsePrereqs(String prereqs) {
        ArrayList<Object> parsedPrereqs = new ArrayList<>();
        String[] requiredParts = prereqs.split("&");
        for (String part: requiredParts){
            if (part.contains("|")){
                part = part.replaceAll("[()]","");
                String[] alternatives = part.split("\\|");
                parsedPrereqs.add(Arrays.asList(alternatives));
            } else {
                parsedPrereqs.add(part);
            }
        }
        return parsedPrereqs;
    }

    /**
     * Helper method that reads data from csv file at csvPath
     *
     * @return an ArrayList of String arrays containing each course's course number, prerequisites,
     * and courses that cannot be taken prior to the course.
     */
    private ArrayList<String[]> readData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath));
            String line = "";
            ArrayList<String[]> courseList = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] courseInfo = line.split(",");
                courseList.add(courseInfo);
            }
            br.close();
            return courseList;
        } catch (FileNotFoundException e) {
            System.out.println("Course information file " + csvPath + " was not found!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
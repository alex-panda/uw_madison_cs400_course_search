// --== CS400 File Header Information ==--
// Name: Allistair Mascarenhas
// Email: anmascarenha@wisc.edu
// Team: DC
// TA: Yelun Bao
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads through and cleans up the data from a csv file
 */
public class DataLoader {
  private ArrayList<Course> data;
  private String csvPath;

  /**
   * Constructor with an argument that initialize data and csvPath by calling the appropriate
   * methods
   * 
   * @param csvPath - name of the csv file
   */
  public DataLoader(String csvPath) {
    this.csvPath = csvPath;
    data = cleanData(readFile());
  }

  /**
   * No argument constructor that calls the other constructor by passing in argument of the default
   * filepath
   */
  public DataLoader() {
    this("DataWrangler/DataWrangler1-Xiaohan/CS_course_info.csv");
  }

  /**
   * Accessor method that returns data
   * 
   * @return data
   */
  public ArrayList<Course> getData() {
    return data;
  }

  /**
   * Helper method that reads through each line of the file. Makes use of try catch to handle any
   * exceptions that arise
   * 
   * @return ArrayList<String[]> - raw data which hasn't yet been cleaned up
   */
  private ArrayList<String[]> readFile() {
    try {
      BufferedReader br = new BufferedReader(new FileReader(csvPath));
      String line = "";
      ArrayList<String[]> rawData = new ArrayList<String[]>();
      String[] temp;
      br.readLine();
      while ((line = br.readLine()) != null) {
        temp = line.split(",");
        rawData.add(temp);
      }
      br.close();
      return rawData;
    } catch (FileNotFoundException e) {
      System.out.println("The file " + csvPath + " does not exist.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Helper method that is used to clean the data
   * 
   * @param rawData - data from csv file that hasn't been cleaned yet
   * @return ArrayList<Course> - ArrayList of all the Course objects from the csv file
   */
  private ArrayList<Course> cleanData(ArrayList<String[]> rawData) {
    ArrayList<Course> courseList = new ArrayList<Course>();

    for (String[] elem : rawData) {
      ArrayList<Object> prereq = new ArrayList<Object>();
      ArrayList<String> noreq = new ArrayList<String>();

      if (!elem[1].equals("None")) {
        String[] split1 = elem[1].split("&");

        for (String curr : split1) {
          if (curr.contains("|")) {
            curr = curr.replace("(", "");
            curr = curr.replace(")", "");
            String[] split2 = curr.split("\\|");
            prereq.add(Arrays.asList(split2));
          } else {
            prereq.add(curr);
          }
        }
      }

      if (!elem[2].equals("None")) {
        noreq = new ArrayList<>(Arrays.asList(elem[2].split("&")));
      }

      Course course = new Course(elem[0], prereq, noreq);
      courseList.add(course);
    }
    return courseList;
  }
}

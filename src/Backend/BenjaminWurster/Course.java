package BenjaminWurster;
import java.util.ArrayList;

public class Course implements Interfaces.CourseInterface {
	private String name;
	private ArrayList<String> prereqs;
	private ArrayList<String> noreqs;

	public Course(String name, ArrayList<String> prereqs, ArrayList<String> noreqs) {
		this.name = name;
		this.prereqs = prereqs;
		this.noreqs = noreqs;
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getPrereqs() {
		return prereqs;
	}

	public ArrayList<String> getNoreqs() {
		return noreqs;
	}

	public boolean hasPrereqs() {
		if(this.prereqs == null || this.prereqs.size() == 0)
			return false;
		return true;
	}

	public boolean hasNoreqs() {
		if(this.noreqs == null || this.noreqs.size() == 0)
			return false;
		return true;
	}
}

import java.util.ArrayList;

public class Course {
	private String name;
	private ArrayList<Object> prereqs;
	private ArrayList<String> noreqs;

	public Course(String name, ArrayList<Object> prereqs, ArrayList<String> noreqs) {
		this.name = name;
		this.prereqs = prereqs;
		this.noreqs = noreqs;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Object> getCoursePrereqs() {
		return prereqs;
	}

	public ArrayList<String> getCourseNoreqs() {
		return noreqs;
	}

	public Boolean hasPrereqs() {
		if(this.prereqs == null || this.prereqs.size() == 0)
			return false;
		return true;
	}

	public Boolean hasNoreqs() {
		if(this.noreqs == null || this.noreqs.size() == 0)
			return false;
		return true;
	}
}

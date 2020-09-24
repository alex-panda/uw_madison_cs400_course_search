import java.util.ArrayList;

public class Course {
	private String name;
	private ArrayList<Object> prereqs;
	private ArrayList<Object> noreqs;
	
	public Course(String name, ArrayList<Object> prereqs, ArrayList<Object> noreqs) {
		this.name = name;
		this.prereqs = prereqs;
		this.noreqs = noreqs;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Object> getPrereqs() {
		return prereqs;
	}
	
	public ArrayList<Object> getNoreqs() {
		return noreqs;
	}
	
	public Boolean hasPrereqs() {
		if(this.prereqs == null || this.prereqs.size() == 0)
			return false;
		return true;
	}
}

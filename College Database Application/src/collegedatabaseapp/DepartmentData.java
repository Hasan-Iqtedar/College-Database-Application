package collegedatabaseapp;

/**A class to query department table.*/
public class DepartmentData {

	private String DepartmentName;
	private String DepartmentID;
	private String InstructorID;

	DepartmentData(String departmentName, String departmentID, String instructorID) {
		this.DepartmentName = departmentName;
		this.DepartmentID = departmentID;
		this.InstructorID = instructorID;
	}

	public String getDepartmentName() {
		return DepartmentName;
	}

	public void setDepartmentName(String departmentName) {
		DepartmentName = departmentName;
	}

	public String getDepartmentID() {
		return DepartmentID;
	}

	public void setDepartmentID(String departmentID) {
		DepartmentID = departmentID;
	}

	public String getInstructorID() {
		return InstructorID;
	}

	public void setInstructorID(String instructorID) {
		InstructorID = instructorID;
	}
	
	
	
}

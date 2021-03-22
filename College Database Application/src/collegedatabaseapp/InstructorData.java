package collegedatabaseapp;

/**A class to query instructor table.*/
public class InstructorData {

	private String InstructorName;
	private String InstructorId;
	private String InstructorRank;
	private String office;
	private String DepartmentId;


	InstructorData(String instructorName, String instructorId, String instructorRank, String office,
			String departmentId) {
		this.InstructorName = instructorName;
		this.InstructorId = instructorId;
		this.InstructorRank = instructorRank;
		this.office = office;
		this.DepartmentId = departmentId;
	}


	public String getInstructorName() {
		return InstructorName;
	}

	public void setInstructorName(String instructorName) {
		InstructorName = instructorName;
	}

	public String getInstructorId() {
		return InstructorId;
	}

	public void setInstructorId(String instructorId) {
		InstructorId = instructorId;
	}

	public String getInstructorRank() {
		return InstructorRank;
	}

	public void setInstructorRank(String instructorRank) {
		InstructorRank = instructorRank;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}

}

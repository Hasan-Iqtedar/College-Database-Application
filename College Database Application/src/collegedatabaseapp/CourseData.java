package collegedatabaseapp;

/**A class to query course table.*/
public class CourseData {

	private String CourseName;
	private String CourseId;
	private String DepartmentId;
	private String InstructorId;

	CourseData(String CourseName, String DepartmentId,String InstructorId,String CourseId){
		this.CourseName = CourseName;
		this.DepartmentId = DepartmentId;
		this.InstructorId = InstructorId;
		this.CourseId = CourseId;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getCourseId() {
		return CourseId;
	}

	public void setCourseId(String courseId) {
		CourseId = courseId;
	}

	public String getDepartmentId() {
		return DepartmentId;
	}

	public void setDepartmentId(String departmentId) {
		DepartmentId = departmentId;
	}

	public String getInstructorId() {
		return InstructorId;
	}

	public void setInstructorId(String instructorId) {
		InstructorId = instructorId;
	}
	
}

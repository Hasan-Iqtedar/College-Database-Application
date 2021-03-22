package collegedatabaseapp;

/**A class to query the tables to extract student course info.*/
public class TakesData {

	private String sname;
	private String cname;
	private String ins_name;
	private String dept_name;

	public TakesData(String sname, String cname, String ins_name, String dept_name) {
		this.sname = sname;
		this.cname = cname;
		this.ins_name = ins_name;
		this.dept_name = dept_name;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public void setIns_name(String ins_name) {
		this.ins_name = ins_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getSname() {
		return sname;
	}

	public String getCname() {
		return cname;
	}

	public String getIns_name() {
		return ins_name;
	}

	public String getDept_name() {
		return dept_name;
	}
}//End of class TakesData.

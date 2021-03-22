package collegedatabaseapp;

/**A class to query student table.*/
public class StudentData{

    private String s_id;
    private String name;
    private String level;
    private String year;
    private String status;
    private String gpa;
    private String major;
    private String city;
    private String language;
    private String bloodGroup;
    private String age;

    StudentData(String s_id, String name, String level, String year,
                String status, String gpa, String major, String city,
                String language, String bloodGroup, String age) {
        this.s_id = s_id;
        this.name = name;
        this.level = level;
        this.year = year;
        this.status = status;
        this.gpa = gpa;
        this.major = major;
        this.city = city;
        this.language = language;
        this.bloodGroup = bloodGroup;
        this.age = age;
    }


    public String getS_id() {
        return s_id;
    }

    public String getName(){
        return name;
    }

    public String getLevel() {
        return level;
    }

    public String getYear() {
        return year;
    }

    public String getStatus() {
        return status;
    }

    public String getGpa() {
        return gpa;
    }

    public String getMajor() {
        return major;
    }

    public String getCity() {
        return city;
    }

    public String getLanguage() {
        return language;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getAge() {
        return age;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setAge(String age) {
        this.age = age;
    }
}//End of class StudentData.


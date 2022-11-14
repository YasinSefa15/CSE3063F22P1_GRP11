package iteration1.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Course extends Model{
    private String name;
    private String code;
    private int credit;
    private  int requiredCredits;
    private int quota;
    private int semester;
    private ArrayList<String> preRequisiteCourses = new ArrayList<String>();
    private ArrayList<String> weeklyHours = new ArrayList<String>();
    private ArrayList<Student> registeredStudents = new ArrayList<Student>();

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public int getCredit() {
        return credit;
    }
    public int getRequiredCredits() {
        return requiredCredits;
    }
    public int getQuota() {
        return quota;
    }

    public int getSemester() {
        return semester;
    }

    public ArrayList<String> getPreRequisiteCourses() {
        return preRequisiteCourses;
    }
    public ArrayList<String> getWeeklyHours() {
        return weeklyHours;
    }
    public void setWeeklyHours(ArrayList<String> weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }
    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

}


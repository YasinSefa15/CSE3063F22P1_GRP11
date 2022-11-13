package iteration1.Models;

import java.util.ArrayList;

public abstract class Course extends Model{
    private String name;
    private String code;
    private int credit;
    private  int requiredCredits;
    private int quota;
    private int semester;
    private ArrayList<Integer> preRequisiteCourses = new ArrayList<Integer>();
    private int[][] weeklyHours = new int[5][8];
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

    public ArrayList<Integer> getPreRequisiteCourses() {
        return preRequisiteCourses;
    }

    public int[][] getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(int[][] weeklyHours) {
        this.weeklyHours = weeklyHours;
    }
    public ArrayList<Student> getRegisteredStudents() {
        return registeredStudents;
    }
    public void setRegisteredStudents(ArrayList<Student> registeredStudents) {

    }

}


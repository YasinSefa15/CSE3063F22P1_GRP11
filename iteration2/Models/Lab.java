package iteration2.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Lab extends Course {
    private int sectionNumber;

    public Lab(){}
    public Lab(String name, String code, int credit, int requiredCredits, int quota, int semester,
               ArrayList<Course> preRequisiteCourses, ArrayList<String> weeklyHours,
               ArrayList<Student> registeredStudents) {
        this.setName(name);
        this.setCode(code);
        this.setCredit(credit);
        this.setRequiredCredits(requiredCredits);
        this.setQuota(quota);
        this.setSemester(semester);
        this.setPreRequisiteCourses(preRequisiteCourses);
        this.setWeeklyHours(weeklyHours);
        this.setRegisteredStudents(registeredStudents);
        this.sectionNumber = -1;
    }

    public void generateSectionNumber(){
        this.sectionNumber = (int)Math.floor(Math.random()*(4)+1);
    }
    public int getSectionNumber() {
        return sectionNumber;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

package iteration1.Models;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mandatory extends Course {
    private ArrayList<Lab> lab = new ArrayList<>();

    public Mandatory(String name, String code, int credit, int requiredCredits, int quota, int semester,
                     ArrayList<String> preRequisiteCourses, ArrayList<String> weeklyHours,
                     ArrayList<Student> registeredStudents, ArrayList<Lab> lab){
        this.setName(name);
        this.setCode(code);
        this.setCredit(credit);
        this.setRequiredCredits(requiredCredits);
        this.setQuota(quota);
        this.setSemester(semester);
        this.setPreRequisiteCourses(preRequisiteCourses);
        this.setWeeklyHours(weeklyHours);
        this.setRegisteredStudents(registeredStudents);
        this.lab = lab;
    }
    public void assignQuota(){

    }
    public ArrayList<Lab> getLab() {
        return lab;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

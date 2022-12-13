package iteration2.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Mandatory extends Course {

    public Mandatory(String name, String code, int credit, int requiredCredits, int quota, int semester,
                     ArrayList<Course> preRequisiteCourses, ArrayList<String> weeklyHours) {
        this.setName(name);
        this.setCode(code);
        this.setCredit(credit);
        this.setRequiredCredits(requiredCredits);
        this.setQuota(quota);
        this.setSemester(semester);
        this.setPreRequisiteCourses(preRequisiteCourses);
        this.setWeeklyHours(weeklyHours);
    }

    @Override
    public JSONObject toJson() {
        return super.toJson();
    }
}

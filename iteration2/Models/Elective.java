package iteration2.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Elective extends Course{
    private ElectiveType type;

    public Elective(String name, String code, int credit, int requiredCredits, int quota, int semester,
                    ArrayList<Course> preRequisiteCourses, ArrayList<String> weeklyHours,
                    ElectiveType type) {
        this.setName(name);
        this.setCode(code);
        this.setCredit(credit);
        this.setRequiredCredits(requiredCredits);
        this.setQuota(quota);
        this.setSemester(semester);
        this.setPreRequisiteCourses(preRequisiteCourses);
        this.setWeeklyHours(weeklyHours);
        this.setType(type);
    }

    public void setType (ElectiveType type) {
        this.type = type;
    }

    public Object getType() {
        return type;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

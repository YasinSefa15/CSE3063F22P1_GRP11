package iteration2.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Elective extends Course{
    private final ElectiveType type;

    public Elective(String name, String code, int credit, int requiredCredits, int quota, int semester,
                    ArrayList<Course> preRequisiteCourses, ArrayList<String> weeklyHours,
                    ArrayList<Student> registeredStudents, ElectiveType type) {
        this.setName(name);
        this.setCode(code);
        this.setCredit(credit);
        this.setRequiredCredits(requiredCredits);
        this.setQuota(quota);
        this.setSemester(semester);
        this.setPreRequisiteCourses(preRequisiteCourses);
        this.setWeeklyHours(weeklyHours);
        this.setRegisteredStudents(registeredStudents);
        this.type = type;
    }

    public ElectiveType getType() {
        return type;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

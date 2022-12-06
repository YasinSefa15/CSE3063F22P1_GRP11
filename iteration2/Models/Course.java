package iteration2.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Course extends Model {
    private String name;
    private String code;
    private int credit;
    private int requiredCredits;
    private int quota;
    private int semester;
    private ArrayList<Course> preRequisiteCourses = new ArrayList<Course>();
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

    public ArrayList<Course> getPreRequisiteCourses() {
        return preRequisiteCourses;
    }

    public ArrayList<String> getWeeklyHours() {
        return weeklyHours;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setRequiredCredits(int requiredCredits) {
        this.requiredCredits = requiredCredits;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setPreRequisiteCourses(ArrayList<Course> preRequisiteCourses) {
        this.preRequisiteCourses = preRequisiteCourses;
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

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("code", this.code);
        jsonObject.put("credit", this.credit);
        jsonObject.put("requiredCredits", this.requiredCredits);
        jsonObject.put("quota", this.quota);
        jsonObject.put("semester", this.semester);
        jsonObject.put("preRequisiteCourses", toJsonArray(this.preRequisiteCourses));
        jsonObject.put("weeklyHours", this.weeklyHours);
        jsonObject.put("registeredStudents", this.registeredStudents);
        return jsonObject;
    }

    public JSONArray toJsonArray(ArrayList<Course> preRequisiteCourses) {
        JSONArray jsonArray = new JSONArray();

        for (Course course : preRequisiteCourses) {
            jsonArray.put(course.toJson());
        }

        return jsonArray;
    }

}


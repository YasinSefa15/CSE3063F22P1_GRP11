package iteration1.Models;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Student extends Person {

    private String id;
    private int registerDate;
    private Boolean isGraduate;
    private int semesterNo;
    private Transcript transcript;

    private ArrayList<String> errors;
    private Advisor advisor;
    private HashMap<Course, Boolean> selectedCourses;

    public Student(String name, String surname, String ssn, Character gender, String id, Boolean isGraduate, int registerDate, int semesterNo, Transcript transcript, Advisor advisor) {
        super(name, surname, ssn, gender);

        this.id = id;
        this.registerDate = registerDate;
        this.isGraduate = isGraduate;
        this.semesterNo = semesterNo;
        this.transcript = transcript;
        this.advisor = advisor;
        this.errors = new ArrayList<>();
    }

    public void generateStudentID() {

    }

    public String getId() {
        return id;
    }

    public int getRegisterDate() {
        return registerDate;
    }

    public Boolean getIsGraduate() {
        return isGraduate;
    }

    public int getSemesterNo() {
        return semesterNo;
    }

    public Transcript getTranscript() {
        return transcript;
    }

    public ArrayList<String> getErrors() {
        return errors;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public HashMap<Course, Boolean> getSelectedCourses() {
        return selectedCourses;
    }

    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    public void setTranscript(Transcript transcript) {
        this.transcript = transcript;
    }

    public void setSelectedCourses(HashMap<Course, Boolean> selectedCourses) {
        this.selectedCourses = selectedCourses;
    }

    public void addError(String errorMessage) {
        this.errors.add(errorMessage);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public String getSsn() {
        return super.getSsn();
    }

    @Override
    public Character getGender() {
        return super.getGender();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", getName());
        jsonObject.put("surname", getSurname());
        jsonObject.put("ssn", getSsn());
        jsonObject.put("gender", getGender());
        jsonObject.put("id", getId());
        jsonObject.put("isGraduate", getIsGraduate());
        jsonObject.put("registerDate", getRegisterDate());
        jsonObject.put("semesterNo", getSemesterNo());
        jsonObject.put("Transcript", getTranscript().toJson());
        JSONObject advisorjson = new JSONObject();
        advisorjson.put("name", advisor.getName());
        advisorjson.put("surname", advisor.getSurname());
        advisorjson.put("ssn", advisor.getSsn());
        jsonObject.put("Advisor", advisorjson);
        jsonObject.put("errors", getErrors());

        return jsonObject;
    }


    public void addToSelectedCourses(Course course, Boolean statu) {
        if (!selectedCourses.containsKey(course)) {
            selectedCourses.put(course, statu);
        }
    }
}
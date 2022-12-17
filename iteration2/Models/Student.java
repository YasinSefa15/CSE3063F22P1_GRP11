package iteration2.Models;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Student extends Person {

    private String id;
    private int registerDate;
    private Boolean isGraduate;
    private int semesterNo;
    private Transcript transcript;

    private ArrayList<String> errors;
    private Advisor advisor;
    private HashMap<Course, Boolean> selectedCourses;

    private static String FILE_PATH="/iteration2/Logs/Student.log";
    private static Logger logger=Logger.getLogger(Student.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler(System.getProperty("user.dir")+FILE_PATH,true);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void customLog(boolean type,String message){
        SimpleFormatter formatter =new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        if(type)
            logger.info(message);
        else
            logger.warning(message);
    }
    public Student(String name, String surname, String ssn, Character gender, String id, Boolean isGraduate, int registerDate, int semesterNo, Transcript transcript, Advisor advisor) {
        super(name, surname, ssn, gender);

        this.id = id;
        this.registerDate = registerDate;
        this.isGraduate = isGraduate;
        this.semesterNo = semesterNo;
        this.transcript = transcript;
        this.advisor = advisor;
        this.errors = new ArrayList<>();

        customLog(true, "Student constructor method is called and new student object is generated.");
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


    public void addToSelectedCourses(Course course, Boolean status) {
        if (!selectedCourses.containsKey(course)) {
            selectedCourses.put(course, status);
            customLog(true, "Checked if the course is completed before and it is not completed. Added to selected courses");
        }
        else {
            customLog(false, "Checked if the course is completed before and it is completed. Course cannot be added");
        }
    }
}
package iteration2.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Advisor extends Person {
    private ArrayList<Student> students;
    private RegistrationError error;

    private static String file_path="/iteration2/Logs/Advisor.log";
    private static Logger logger=Logger.getLogger(Advisor.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler(System.getProperty("user.dir")+file_path,true);
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

    public Advisor(ArrayList<Student> students, String name, String surname, String ssn, Character gender, RegistrationError error) {
        super(name,surname,ssn,gender);
        this.students = students;
        this.error = error;
        customLog(true, "Advisor constructor method is called and new advisor object is generated.");
    }

    public boolean courseAvailability(Student student, Course course) {

        if (!checkSemester(student, course)) {
            student.addError(this.error.reportError(1001,new String[]{course.getName(),Integer.toString(course.getSemester()), Integer.toString(student.getSemesterNo())}));
            customLog(false, "Checked semester of students and courses that requested by students and they are declined ");
            return false;
        }
        if(!checkCredit(student, course)) {
            student.addError(this.error.reportError(1002,new String[]{course.getName(),Integer.toString(student.getTranscript().getCompletedCredit())}));
            customLog(false, "Compared credit of students and required credit of course and it is not enough.");
            return false;
        }
        if(!checkQuota(course)) {
            student.addError(this.error.reportError(1003,new String[]{course.getName()}));
            customLog(false, "Check quota of course and there is not enough space for student.");
            return false;
        }
        Object[] tempReturn = checkPreRequisite(student, course);
        if(!(boolean)tempReturn[0]) {
            student.addError(this.error.reportError(1004,new String[]{course.getName(),(String)tempReturn[1]}));
            //return false;
        }
        Object[] tempReturn2 = checkCollision(student);
        if(!(boolean)tempReturn2[0]) {
            student.addError(this.error.reportError(1005,new String[]{(String)tempReturn2[1],(String)tempReturn2[2],
                    (String)tempReturn2[3]}));
            //return false;
        }
        if(!checkElective(student, course)) {
            //1005
        }
        if(!FTETakeable(student, course)) {
            //1006
        }
        customLog(true, "courseAvailability method is called and advisor check the course availability.");
        return true;
    }

    public boolean checkSemester(Student student, Course course) {
        if(course.getSemester() <= student.getSemesterNo()) {
            return true;
        }
        return false;
    }

    public boolean checkQuota(Course course) {
        if(course.getRegisteredStudentsCount() >= course.getQuota()) {
            return false;
        }
        return true;
    }
    public Object[] checkPreRequisite(Student student, Course course) {
        ArrayList<Course> tempCompletedCourse = student.getTranscript().getCompletedCourses();
        boolean availability = true;
        Object[] errorInfo = new Object[2];
        for (int i=0; i<course.getPreRequisiteCourses().size();i++){
            for (int j = 0; j<tempCompletedCourse.size();j++){
                //TO-DO : check if the course is in the completed courses
                if (course.getPreRequisiteCourses().get(i).getCode()!=tempCompletedCourse.get(j).getCode()){
                    availability = false;
                    errorInfo[1] = course.getPreRequisiteCourses().get(i).getCode();
                }
                else {
                    availability = true;
                    errorInfo[1] = "";
                    break;
                }
            }
            if(!availability){break;}
        }
        errorInfo[0] = availability;
        return errorInfo;
    }

    public boolean checkCredit(Student student, Course course) {
        if(course.getRequiredCredits() > student.getTranscript().getCompletedCredit()) {
            return false;
        }
        return true;
    }

    public Object[] checkCollision(Student student) {
        Set<Course> coursesOfHash = student.getSelectedCourses().keySet();
        ArrayList<Course> courses = new ArrayList<>(coursesOfHash);
        Object[] errorInfo = new Object[4];
        errorInfo[0] = true;
        errorInfo[1] = "";
        errorInfo[2] = "";
        errorInfo[3] = "";
        for(int i=0; i<courses.size(); i++){
            ArrayList<String> temp = new ArrayList<>(courses.get(i).getWeeklyHours());
            for (int j = i+1; j< courses.size();j++){
                temp.retainAll(courses.get(j).getWeeklyHours());
                if (temp.size() != 0){
                    errorInfo[0] = false;
                    errorInfo[1] = courses.get(i).getName();
                    errorInfo[2] = courses.get(j).getName();
                    errorInfo[3] = Integer.toString(temp.size());
                    return errorInfo;
                }
            }
        }
        return errorInfo;
    }

    public boolean checkElective(Student student, Course course) {
        AtomicInteger count = new AtomicInteger();
        student.getSelectedCourses().forEach((k,v)->{
            if (k.getClass().equals(Elective.class))
                count.getAndIncrement();


        });
        if (count.get() >=2){
            return false;
        }
        return true;
    }

    public boolean FTETakeable(Student student, Course course) {
        if(student.getSemesterNo()>= 8) {
            return true;
        }
        return false;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudents(ArrayList<Student> students) {
        for (int i=0; i<students.size();i++){
            for (int j = 0; j<this.students.size();j++){
                if (students.get(i).getId()!=this.students.get(j).getId()){
                    this.students.add(students.get(i));
                }
            }
        }
    }

    public JSONArray convertArraylistToJsonArray(ArrayList<Student> arrayList){
        JSONArray jsonArray=new JSONArray();
        for (int i=0;i<arrayList.size();i++){
            jsonArray.put(arrayList.get(i).getName() + " " + arrayList.get(i).getSurname());
        }
        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("students",convertArraylistToJsonArray(getStudents()));
        jsonObject.put("name",getName());
        jsonObject.put("surname",getSurname());
        jsonObject.put("ssn",getSsn());
        jsonObject.put("gender",getGender());
        return jsonObject;
    }
}


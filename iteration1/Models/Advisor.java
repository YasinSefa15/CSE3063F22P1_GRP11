package iteration1.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Advisor extends Person {
    private ArrayList<Student> students;

    public Advisor(ArrayList<Student> students, String name, String surname, String ssn, Character gender) {
        super(name,surname,ssn,gender);
        this.students = students;
    }

    public boolean courseAvailability(Student student, Course course) {
        if(course.getSemester() == student.getSemesterNo()) {
            return true;
        }
        return false;
    }

    public boolean checkQuota(Course course) {
        if(course.getRegisteredStudents().size() < course.getQuota()) {
            return true;
        }
        return false;
    }
    public String checkPreRequisite(Student student, Course course) {
        ArrayList<Course> tempCompletedCourse = student.getTranscript().getCompletedCourses();
        boolean error = false;
        for (int i=0; i<course.getPreRequisiteCourses().size();i++){
            for (int j = 0; j<tempCompletedCourse.size();j++){
                //TO-DO : check if the course is in the completed courses
                if (course.getPreRequisiteCourses().get(i).getCode()!=tempCompletedCourse.get(j).getCode()){
                    error = true;
                }
                else {
                error = false;
                    break;
                }
            }
        }
        if(error){return "You cannot enroll in this course because you have not completed the prerequisite course.";}
        return "";
    }

    public boolean checkCredit(Student student, Course course) {
        if(course.getRequiredCredits() <= student.getTranscript().getCompletedCredit()) {
            return true;
        }
        return false;
    }

    public boolean checkCollision(HashMap<Course, Boolean> selectedCourses) {
        Set<Course> coursesOfHash = selectedCourses.keySet();
        ArrayList<Course> courses = new ArrayList<>(coursesOfHash);
        for(int i=0; i<courses.size(); i++){
            ArrayList<String> temp = new ArrayList<>(courses.get(i).getWeeklyHours());
            for (int j = i+1; j< courses.size();j++){
                temp.retainAll(courses.get(j).getWeeklyHours());
                if (temp.size() != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkElective(Student student, Course course) {
        return false;
    }

    public boolean FTETakeable(Student student, Course course) {
        if(course.getSemester() >= 8) {
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
            jsonArray.put(arrayList.get(i));
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


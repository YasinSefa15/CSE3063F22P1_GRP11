package iteration1.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Advisor extends Person {
    private ArrayList<Student> students;

    public boolean courseAvailibality(Student student, Course course) {
        return false;
    }

    public boolean chechQuota(Student student, Course course) {
        return false;
    }

    public String checkPreRequisite(Student student, Course course) {

        ArrayList<Course> tempCompletedCourse = student.getTranscript().getCompletedCourses();

        for(Integer currentCourse : course.getPreRequisiteCourses()) {
            if(!tempCompletedCourse.contains(course)) {
                return "You cannot enroll in this course because you have not completed the prerequisite course.";
            }
            else {
                return  "You enrolled in this course successfully";
            }

        }
        return "There is no pre-requisite course";
    }

    public int checkCredit(Student student, Course course) {
        return 1;
    }

    public boolean checkCollision(Student student, Course course) {
        return false;
    }

    public boolean checkElective(Student student, Course course) {
        return false;
    }

    public boolean FTETakeable(Student student, Course course) {
        return false;
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<Student>();
    }

    public void setStudents(ArrayList<Student> students) {

    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}


package iteration1.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Advisor extends Person {
    private ArrayList<Student> students;

    public Advisor(ArrayList<Student> students, String name, String surname, String Ssn, char gender) {
        this.students = students;
        super.setName(name);
        super.setSurname(surname);
        super.setSsn(Ssn);
        super.setGender(gender);
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
        for (int i=0; i<tempCompletedCourse.size();i++){
            for (int j = 0; j<course.getPreRequisiteCourses().size();j++){
                if (tempCompletedCourse.get(i).getCode()!=course.getPreRequisiteCourses().get(j).toString()){
                    return "You cannot enroll in this course because you have not completed the prerequisite course.";
                }
                else {
                    return  "You enrolled in this course successfully";
                }
            }
        }
        return "There is no pre-requisite course";
    }

    public boolean checkCredit(Student student, Course course) {
        if(course.getRequiredCredits() <= student.getTranscript().getCompletedCredit()) {
            return true;
        }
        return false;
    }

    public boolean checkCollision(Student student, Course course) {
        ArrayList<Course> approvedCourses = new ArrayList<>();
        for(int i = 0; i < approvedCourses.size() - 1;i++) {
            for(int j = i + 1; j < approvedCourses.size();j++) {

            }
        }
        return false;
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



    @Override
    public JSONObject toJson() {
        return null;
    }
}


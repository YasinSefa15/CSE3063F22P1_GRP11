package iteration1.Controllers;

import iteration1.Models.Advisor;
import iteration1.Models.Course;
import iteration1.Models.Curriculum;
import iteration1.Models.Student;
import org.json.JSONObject;

import java.util.ArrayList;

public class LabelingController extends Controller {
    ArrayList<Student> students;
    ArrayList<Advisor> advisors;

    public void execute() {
        initObjects();
        initAdvisors();
    }

    public void initObjects() {
        initStudents();
    }

    public ArrayList<Course> initCourses() {
        return null;
    }

    public ArrayList<Student> initStudents() {
        students = new ArrayList<>();
        ArrayList<JSONObject> objects = readJSONFiles("Students");
        objects.forEach((n) -> students.add(new Student(
                n.getString("id"),
                n.getInt("registerDate"),
                n.getInt("semesterNo"),
                null,
                null
        )));
        return students;
    }

    public Curriculum initCurriculum() {
        return null;
    }

    public ArrayList<Advisor> initAdvisors() {
        advisors = new ArrayList<>();
        ArrayList<JSONObject> objects = readJSONFiles("Students");
        objects.forEach((n) -> advisors.add(new Advisor(
                null,
                n.getString("name"),
                n.getString("surname"),
                n.getString("ssn"),
                n.getString("gender").charAt(0)
        )));
        return advisors;
    }
}

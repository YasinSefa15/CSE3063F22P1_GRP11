package iteration1.Controllers;

import iteration1.Models.*;
import org.json.JSONObject;

import java.util.ArrayList;

public class LabelingController extends Controller {
    ArrayList<Student> students;
    ArrayList<Advisor> advisors;
    ArrayList<Curriculum> curriculums;
    ArrayList<Course> courses;

    public void execute() {
        initObjects();
        SimulationController simulationController = new SimulationController(courses, students, curriculums, advisors);
        simulationController.setError(this.getError());
        simulationController.startSimulation();
    }

    public void initObjects() {
        initAdvisors();
        initCoursesAndCurriculum();
        initStudents();
    }

    public void initCoursesAndCurriculum() {

    }

    public void initCourses() {

    }

    public void initStudents() {
    }

    public void initCurriculum() {

    }

    public void initAdvisors() {
        advisors = new ArrayList<>();
        ArrayList<JSONObject> objects = readJSONFiles("Students");
        objects.forEach((n) -> advisors.add(new Advisor(
                null,
                n.getString("name"),
                n.getString("surname"),
                n.getString("ssn"),
                n.getString("gender").charAt(0)
        )));
    }
}

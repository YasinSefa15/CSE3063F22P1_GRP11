package iteration1.Models;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Curriculum extends Course {

    HashMap<Integer, ArrayList<Course>> courses = new HashMap<Integer, ArrayList<Course>>();

    public HashMap<Integer, ArrayList<Course>> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<Integer, ArrayList<Course>> courses) {
        this.courses = courses;
    }

    public boolean addCourseToSemester(Course course) {
        return false;
    }


    @Override
    public JSONObject toJson() {
        return null;
    }
}

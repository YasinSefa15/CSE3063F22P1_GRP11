package iteration1.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Curriculum {

    HashMap<Integer, ArrayList<Course>> courses = new HashMap<Integer, ArrayList<Course>>();

    public HashMap<Integer, ArrayList<Course>> getCourses() {
        return courses;
    }

    public void setCourses(HashMap<Integer, ArrayList<Course>> courses) {
        this.courses = courses;
    }

    public boolean addCourseToSemester(Course course) {
        if(courses.get(course.getSemester()) == null) {
            courses.put(course.getSemester(), new ArrayList<>());
            courses.get(course.getSemester()).add(course);
        }
        else {
            courses.get(course.getSemester()).add(course);
        }
        return true;
    }
}

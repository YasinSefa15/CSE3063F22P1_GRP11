package iteration2.Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Curriculum {

    private HashMap<Integer, ArrayList<Course>> courses = new HashMap<Integer, ArrayList<Course>>();

    public HashMap<Integer, ArrayList<Course>> getCourses() {
        return courses;
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

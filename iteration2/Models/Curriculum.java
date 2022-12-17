package iteration2.Models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Curriculum {

    private HashMap<Integer, ArrayList<Course>> courses = new HashMap<Integer, ArrayList<Course>>();

    public HashMap<Integer, ArrayList<Course>> getCourses() {
        return courses;
    }
    private static String file_path="/iteration2/Logs/Curriculum.log";
    private static Logger logger=Logger.getLogger(Curriculum.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler(System.getProperty("user.dir")+file_path,true);
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void customLog(boolean type,String message){
        SimpleFormatter formatter =new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        if(type)
            logger.info(message);
        else
            logger.warning(message);
    }
    public boolean addCourseToSemester(Course course) {
        if(courses.get(course.getSemester()) == null) {
            courses.put(course.getSemester(), new ArrayList<>());
            courses.get(course.getSemester()).add(course);
            customLog(true, "Created new semester array list for courses and added courses to semester.");
        }
        else {
            courses.get(course.getSemester()).add(course);
            customLog(true, "Added courses to semester.");
        }
        return true;
    }
}

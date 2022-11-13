package iteration1.Controllers;

import iteration1.Models.Course;
import iteration1.Models.Curriculum;
import iteration1.Models.Student;
import iteration1.Models.Advisor;

import java.util.ArrayList;
import java.util.Random;

public class SimulationController extends Controller {
    ArrayList<Course> courses;
    ArrayList<Student> students;
    ArrayList<Advisor> advisors;
    Curriculum curriculum;

    SimulationController(ArrayList<Course> courses, ArrayList<Student> students,
                         Curriculum curriculum, ArrayList<Advisor> advisors) {
        this.courses = courses;
        this.students = students;
        this.curriculum = curriculum;
        this.advisors = advisors;
    }

    public void startSimulation() {
        registerStudentsToClasses();
    }

    public void registerStudentsToClasses() {
        StudentController studentController = new StudentController();
        studentController.setError(this.getError());

        Random randomizer = new Random();
        int studentsCount = students.size();
        Student student = null;
        for (int i = 0; i < studentsCount; i++) {
            student = students.get(i);
            Course course = null;

            //todo : burası ile oynanır sonra.
            while (student.getSelectedCourses().size() != 8) {
                course = randomCourse(student);
                studentController.registerToCourse(student, course);
            }
        }

    }

    public Course randomCourse(Student student) {
        Random randomizer = new Random();
        Course course = null;
        boolean ableToRegister = false;

        while (!ableToRegister) {
            course = courses.get(randomizer.nextInt(courses.size()));
            if (student.getTranscript().getCompletedCourses().contains(course)
                    || student.getTranscript().getFailedCourses().contains(course)) {
                ableToRegister = true;
            }
        }

        return course;
    }

}

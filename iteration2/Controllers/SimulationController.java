package iteration2.Controllers;

import iteration2.Models.Course;
import iteration2.Models.Curriculum;
import iteration2.Models.Student;
import iteration2.Models.Advisor;

import java.util.ArrayList;
import java.util.Random;

public class SimulationController extends Controller {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    private ArrayList<Curriculum> curriculums;

    private ArrayList<Course> deniedCourses;

    SimulationController(ArrayList<Course> courses, ArrayList<Student> students,
                         ArrayList<Curriculum> curriculums, ArrayList<Advisor> advisors) {
        this.courses = courses;
        this.students = students;
        this.curriculums = curriculums;
        this.advisors = advisors;
    }

    public void startSimulation() {
        registerStudentsToClasses();
        exportObjects();
    }

    private void exportObjects() {
        for (Advisor advisor : advisors) {
            exportJSONFile(advisor);
        }
        for (Student student : students) {
            student.getTranscript().calculateGPA();
            exportJSONFile(student);
        }

        exportJSONFile(this.getError());
    }

    public void registerStudentsToClasses() {
        StudentController studentController = new StudentController();
        studentController.setError(this.getError());
        this.deniedCourses = new ArrayList<>();

        Random randomizer = new Random();
        int studentsCount = students.size();
        Student student = null;
        for (int i = 0; i < studentsCount; i++) {
            student = students.get(i);
            Course course = null;
            this.deniedCourses.clear();

            //todo : can change later
            while (student.getSelectedCourses().size() != 5) {
                course = randomCourse(student);

                if (!studentController.registerToCourse(student, course)) {
                    deniedCourses.add(course);
                }
            }
        }
    }

    public Course randomCourse(Student student) {
        Random randomizer = new Random();
        Course course = null;
        boolean ableToRegister = false;

        while (!ableToRegister) {
            course = courses.get(randomizer.nextInt(courses.size()));
            if ((!student.getTranscript().getCompletedCourses().contains(course)
                    || !student.getTranscript().getFailedCourses().contains(course)
                    && !deniedCourses.contains(course))) {
                ableToRegister = true;
            }
        }

        return course;
    }
}

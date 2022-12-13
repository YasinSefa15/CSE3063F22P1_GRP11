package iteration2.Controllers;

import iteration2.Models.Course;
import iteration2.Models.Curriculum;
import iteration2.Models.Student;
import iteration2.Models.Advisor;

import java.util.ArrayList;


public class SimulationController extends Controller {
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    private ArrayList<Curriculum> curriculums;

    private ArrayList<Course> deniedCourses;

     SimulationController(ArrayList<Student> students,
                                ArrayList<Curriculum> curriculums, ArrayList<Advisor> advisors) {
        this.students = students;
        this.curriculums = curriculums;
        this.advisors = advisors;
    }

    public void startSimulation() {
        registerStudentsToClasses();
        exportObjects();
        printErrorMessages();
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

    private void printErrorMessages() {
        for (String message : this.getError().getAllErrorMessages()) {
            System.out.println(message);
        }
    }

    public void registerStudentsToClasses() {
        StudentController studentController = new StudentController();
        studentController.setError(this.getError());
        this.deniedCourses = new ArrayList<>();

        int studentsCount = students.size();
        Student student = null;

        for (int i = 0; i < studentsCount; i++) {
            student = students.get(i);
            this.deniedCourses.clear();
            //if student registered 2020 and later first indexed curriculum will be taken consideration
            int studentRegistersDateToCurriculum = student.getRegisterDate() >= 2020 ? 1 : 0;
            int simulationSemester = student.getSemesterNo();
            ArrayList<Course> courses = curriculums.get(studentRegistersDateToCurriculum).getCourses().get(simulationSemester);

            if (courses != null) {
                for (Course course : courses) {
                    if (!studentController.registerToCourse(student, course)) {
                        deniedCourses.add(course);
                    }
                }
            } else {
                System.out.println("courses  is null " + student.getSemesterNo());
            }

        }
    }



    public ArrayList<Advisor> getAdvisors() {
        return this.advisors;
    }
}

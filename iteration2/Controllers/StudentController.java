package iteration2.Controllers;

import iteration2.Models.Course;
import iteration2.Models.Student;

public class StudentController extends Controller {

    public boolean registerToCourse(Student student, Course course) {
        boolean permission = student.getAdvisor().courseAvailability(student, course);

        if(!permission){
            return false;
        }

        //if advisor allows, register to course
        student.addToSelectedCourses(course, changeCourseStatus(student, course));
        return true;
    }

    public boolean changeCourseStatus(Student student, Course course) {
        if ((int) (Math.round(Math.random())) == 1) {
            student.getTranscript().addToCompletedCourses(course);
            student.getTranscript().setCompletedCredit(
                    student.getTranscript().getCompletedCredit() + course.getCredit()
            );
            return true;
        }
        student.getTranscript().addToFailedCourses(course);
        return false;
    }
}
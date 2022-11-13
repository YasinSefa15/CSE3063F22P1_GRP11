package iteration1.Controllers;

import iteration1.Models.Course;
import iteration1.Models.Student;

public class StudentController extends Controller {

    public boolean registerToCourse(Student student, Course course) {
        boolean permission = student.getAdvisor().courseAvailability(student, course);

        if(!permission){
            return false;
        }

        //advisordan geçtiyse
        student.addToSelectedCourses(course, changeCourseStatus(student, course));
        return false;
    }

    //todo : uml güncellenecek
    //transkript e gerek var mı ? student ve course gelsin yeter? buradan güncelleriz
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
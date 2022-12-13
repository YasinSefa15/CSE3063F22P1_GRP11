package iteration2.Tests;

import iteration2.Controllers.LabelingController;
import iteration2.Controllers.StudentController;
import iteration2.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class StudentControllerTest {

    StudentController studentControllerTest;

    @BeforeEach
    void setUp() {
        studentControllerTest = new StudentController();
    }

    @Test
    void registerToCourse() {
        Advisor advisor = new Advisor(new ArrayList<>(), "Borahan",
                "Kaya", "123456789", 'm', new RegistrationError()
        );

        Transcript transcript = new Transcript(0, 0,
                new ArrayList<>(), new ArrayList<>());

        Student student = new Student(
                "John", "Doe", "12345678910", 'm',
                "12345123451", false,
                2020, 4,
                transcript, advisor
        );

        student.setSelectedCourses(new HashMap<>());


        Course course = new Mandatory(
                "name", "code", 3, 0, 10, 5,
                new ArrayList<>(), new ArrayList<>()
        );

        //testing semester
        assertFalse(studentControllerTest.registerToCourse(student, course));
        course.setSemester(2);
        assertTrue(studentControllerTest.registerToCourse(student, course));
        course.setRegisteredStudentsCount(0); //freeing quota


        //testing credit
        student.getTranscript().setCompletedCredit(4);
        course.setRequiredCredits(3);
        assertTrue(studentControllerTest.registerToCourse(student, course));
        course.setRegisteredStudentsCount(0); //freeing quota

        course.setRequiredCredits(5);
        student.getTranscript().setCompletedCredit(4);
        assertFalse(studentControllerTest.registerToCourse(student, course));
        course.setRegisteredStudentsCount(0); //freeing quota

        //testing quota
        student.getTranscript().setCompletedCredit(6);
        assertTrue(studentControllerTest.registerToCourse(student, course));
        //assume there is one different student registered to course
        course.addRegisteredStudent();
        course.setQuota(1);
        assertFalse(studentControllerTest.registerToCourse(student, course));
    }

    @Test
    void changeCourseStatus() {
    }
}
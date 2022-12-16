package iteration2.Tests;

import iteration2.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AdvisorTest {

    Advisor advisorTest = null;
    @BeforeEach
    void setUp() {
        advisorTest=new Advisor(new ArrayList<>(), "","","",null,null);
    }

    @Test
    void checkSemester() {
        Course course1=new Mandatory("CSE1111","1111",4,0,100,2,new ArrayList<>(),new ArrayList<>());
        Student student = new Student("","","",null,"",false,2019,4,null,null);
        assertTrue(advisorTest.checkSemester(student,course1));

        Course course2=new Mandatory("CSE1111","1111",3,60,100,5,new ArrayList<>(),new ArrayList<>());
        assertFalse(advisorTest.checkSemester(student,course2));
    }

    @Test
    void checkQuota() {
        Course course=new Mandatory("CSE1111","1111",4,0,100,2,new ArrayList<>(),new ArrayList<>());
        course.setRegisteredStudentsCount(99);
        assertTrue(advisorTest.checkQuota(course));

        course.setRegisteredStudentsCount(100);
        assertFalse(advisorTest.checkQuota(course));
    }

    @Test
    void checkPreRequisite() {
        Transcript transcript = new Transcript(3.24,60,new ArrayList<>(),new ArrayList<>());
        Student student = new Student("","","",null,"",false,2019,4,transcript,null);
        Course course1=new Mandatory("CSE1111","1111",4,0,100,2,new ArrayList<>(),new ArrayList<>());
        Course course2=new Mandatory("CSE2222","2222",4,0,100,3,new ArrayList<>(),new ArrayList<>());
        Course course3=new Mandatory("CSE1112","1112",4,0,100,2,new ArrayList<>(),new ArrayList<>());

        ArrayList<Course> preRequisiteCourses = new ArrayList<>();
        preRequisiteCourses.add(course3);

        ArrayList<Course> completedCourses = new ArrayList<>();
        completedCourses.add(course1);
        completedCourses.add(course3);

        course2.setPreRequisiteCourses(preRequisiteCourses);
        student.getTranscript().setCompletedCourses(completedCourses);

        assertEquals(true,advisorTest.checkPreRequisite(student,course2)[0]);
    }

    @Test
    void checkCredit() {
        Transcript transcript = new Transcript(3.40,60,new ArrayList<>(),new ArrayList<>());
        Student student = new Student("","","",null,"",false,2019,4,transcript,null);
        Course course1=new Mandatory("CSE1111","1111",6,0,100,2,new ArrayList<>(),new ArrayList<>());
        Course course2=new Elective("CSE4111","4111",5,180,45,7,new ArrayList<>(),new ArrayList<>(),ElectiveType.FTE);

        assertTrue(advisorTest.checkCredit(student,course1));
        assertFalse(advisorTest.checkCredit(student,course2));
    }

    @Test
    void checkCollision() {
        Student student = new Student("","","",'M',"",false,2019,4,null,null);
        Course course1=new Mandatory("CSE1111","1111",4,0,100,2,new ArrayList<>(),new ArrayList<>());
        Course course2=new Mandatory("CSE2222","2222",4,0,100,3,new ArrayList<>(),new ArrayList<>());

        ArrayList<String> weeklyHours1 = new ArrayList<>();
        ArrayList<String> weeklyHours2 = new ArrayList<>();
        weeklyHours1.add("2.3");
        weeklyHours1.add("2.4");
        weeklyHours2.add("2.4");
        weeklyHours2.add("2.5");
        course1.setWeeklyHours(weeklyHours1);
        course2.setWeeklyHours(weeklyHours2);

        HashMap<Course, Boolean> courseOfHashTest = new HashMap<>();
        courseOfHashTest.put(course1,true);
        courseOfHashTest.put(course2,true);

        student.setSelectedCourses(courseOfHashTest);

        assertEquals(false,advisorTest.checkCollision(student)[0]);
    }
}
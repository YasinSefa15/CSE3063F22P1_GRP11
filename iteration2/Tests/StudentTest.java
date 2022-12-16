package iteration2.Tests;

import iteration2.Models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTest {

    Student studentTest = null;

    @BeforeEach
    void SetUp(){
        studentTest=new Student("","","",null,"",false,2019,5, null,null);
    }

    @Test
    void addToSelectedCourses() {
        HashMap<Course, Boolean> studentSelectedCourses = new HashMap<>();
        Course course=new Mandatory("CSE1111","1111",4,2,100,1,new ArrayList<>(),new ArrayList<>());
        ArrayList<String> weeklyHours1 = new ArrayList<>();
        weeklyHours1.add("2.3");
        weeklyHours1.add("2.4");
        course.setWeeklyHours(weeklyHours1);
        studentSelectedCourses.put(course,true);
        studentTest.setSelectedCourses(studentSelectedCourses);
        assertEquals(studentSelectedCourses,studentTest.getSelectedCourses());
    }
}

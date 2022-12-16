package iteration2.Tests;

import iteration2.Models.Course;
import iteration2.Models.Mandatory;
import iteration2.Models.Transcript;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TranscriptTest {

    Transcript transcriptTest=null;

    @BeforeEach
    void SetUp(){
        transcriptTest=new Transcript(3.5,30,new ArrayList<>(),new ArrayList<>());
    }

    @Test
    void calculateGPA(){
        ArrayList<Course>  transcriptCompletedCourses =transcriptTest.getCompletedCourses();
        Course course=new Mandatory("CSE1001","1001",6,2,100,1,new ArrayList<>(),new ArrayList<>());
        transcriptCompletedCourses.add(course);
        transcriptTest.addToCompletedCourses(course);

        ArrayList<Course>  transcriptFailedCourses =transcriptTest.getFailedCourses();
        Course course2=new Mandatory("CSE2002","2002",2,4,200,2,new ArrayList<>(),new ArrayList<>());
        transcriptFailedCourses.add(course2);
        transcriptTest.addToFailedCourses(course2);

        transcriptTest.calculateGPA();
        assertEquals(3,transcriptTest.getGpa());
    }

    @Test
    void addToCompletedCourses() {
        ArrayList<Course>  transcriptCompletedCourses =transcriptTest.getCompletedCourses();
        Course course=new Mandatory("CSE1111","1111",4,2,100,1,new ArrayList<>(),new ArrayList<>());
        transcriptCompletedCourses.add(course);
        transcriptTest.addToCompletedCourses(course);
        assertEquals(transcriptCompletedCourses,transcriptTest.getCompletedCourses());
    }

    @Test
    void addToFailedCourses() {
        ArrayList<Course>  transcriptFailedCourses =transcriptTest.getFailedCourses();
        Course course=new Mandatory("CSE2222","2222",8,4,200,2,new ArrayList<>(),new ArrayList<>());
        transcriptFailedCourses.add(course);
        transcriptTest.addToFailedCourses(course);
        assertEquals(transcriptFailedCourses,transcriptTest.getFailedCourses());
    }

    @Test
    void convertArraylistToJsonArray() {
        ArrayList<Course>  transcriptCompletedCourses=transcriptTest.getCompletedCourses();
        Course course=new Mandatory("CSE3333","3333",6,3,150,3,new ArrayList<>(),new ArrayList<>());
        transcriptCompletedCourses.add(course);
        JSONArray transcriptCompletedCoursesJsonArray=new JSONArray();
        for (int i=0;i<transcriptCompletedCourses.size();i++){
            transcriptCompletedCoursesJsonArray.put(transcriptCompletedCourses.get(i).toJson());
        }
        JSONArray jsonArrayOfFunction=transcriptTest.convertArraylistToJsonArray(transcriptCompletedCourses);
        assertEquals(transcriptCompletedCoursesJsonArray.toString(),jsonArrayOfFunction.toString());
    }
}
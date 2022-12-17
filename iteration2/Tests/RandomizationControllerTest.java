package iteration2.Tests;

import iteration2.Models.Advisor;
import iteration2.Models.Student;
import iteration2.Controllers.LabelingController;
import iteration2.Controllers.RandomizationController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomizationControllerTest {

    RandomizationController randomizationControllerTest = null;
    LabelingController labelingControllerTest = null;

    @BeforeEach
    void setUp(){
        randomizationControllerTest = new RandomizationController(null);
        labelingControllerTest = new LabelingController(null);
    }

    @Test
    void generateStudents() {
    }

    @Test
    void generateTranscript() {
    }

    @Test
    void chooseRandomAdvisor() {
        labelingControllerTest.initCoursesAndCurriculums();
        labelingControllerTest.initAdvisors();
        //chooseRandomAdvisor is called inside generateStudents method
        ArrayList<Student> students = randomizationControllerTest.generateStudents(2, labelingControllerTest.getCourses(), labelingControllerTest.getAdvisors(), 1);
        assertEquals(students.size(),2);
        labelingControllerTest.setStudents(students);
        assertEquals(students.get(0).getAdvisor().getSsn(), labelingControllerTest.getAdvisors().get(0).getSsn());
    }
}
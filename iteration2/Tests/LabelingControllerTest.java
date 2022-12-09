package iteration2.Tests;

import iteration2.Controllers.LabelingController;
import iteration2.Controllers.RandomizationController;
import iteration2.Models.Advisor;
import iteration2.Models.Student;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LabelingControllerTest {

    String ANSI_GREEN = "\u001B[32m";
    String ANSI_RESET = "\u001B[0m";
    LabelingController labelingControllerTest = new LabelingController(null);
    RandomizationController randomizationControllerTest = new RandomizationController(null);

    int advisorCount;

    @Test
    /*void testChooseRandomAdvisor() {
        advisorCount = 0;

        ArrayList<Student> students = randomizationControllerTest.generateStudents(2, new ArrayList<>(), new ArrayList<>(), semester);

        labelingControllerTest.setStudents(students);
        labelingControllerTest.setAdvisors(new ArrayList<>());
        //2 students 1 advisor
        advisorCount++;
        labelingControllerTest.getAdvisors().add(generateAdvisor(advisorCount));

        Advisor returnedAdvisor = labelingControllerTest.chooseRandomAdvisor();
        assertEquals(returnedAdvisor.getSsn(), labelingControllerTest.getAdvisors().get(0).getSsn());
        //if test successful then first student added to advisor
        returnedAdvisor.getStudents().add(students.get(0));

        //2 students 2 advisors. One advisor for each student. note:First adv. have student.
        advisorCount++;
        labelingControllerTest.getAdvisors().add(generateAdvisor(advisorCount));
        returnedAdvisor = labelingControllerTest.chooseRandomAdvisor();
        assertEquals(returnedAdvisor.getSsn(), labelingControllerTest.getAdvisors().get(0).getSsn());
        returnedAdvisor.getStudents().add(students.get(1));

        //Add one more student
        students.add(randomizationControllerTest.generateStudents(1,new ArrayList<>(), new ArrayList<>(), semester).get(0));

        //0 index advisor will be assigned, not the advisor in the first index
        returnedAdvisor = labelingControllerTest.chooseRandomAdvisor();
        assertNotEquals(returnedAdvisor.getSsn(), labelingControllerTest.getAdvisors().get(0).getSsn());

        System.out.println(ANSI_GREEN + "testChooseRandomAdvisor success" + ANSI_RESET);
    }*/


    Advisor generateAdvisor(int ssn) {
        return new Advisor(
                new ArrayList<>(),
                "name",
                "surname",
                String.valueOf(ssn),
                'm',
                null
        );
    }
}
package iteration2.Tests;

import iteration2.Controllers.LabelingController;
import iteration2.Models.Course;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LabelingControllerTest {
    LabelingController labelingControllerTest = null;

    @BeforeEach
    void setUp() {
        labelingControllerTest = new LabelingController(null);
        labelingControllerTest.setCourses(new ArrayList<>());
    }


    @Test
    void createWeeklyHoursList() {
        ArrayList<String> returned = null;

        JSONArray weeklyHours = new JSONArray();
        weeklyHours.put("7.7");
        returned = labelingControllerTest.createWeeklyHoursList(weeklyHours);
        assertEquals(returned.get(0), "7.7");

        weeklyHours.put("7.8");
        returned = labelingControllerTest.createWeeklyHoursList(weeklyHours);
        assertNotEquals(returned.get(0), "7.8");
        assertEquals(returned.get(1), "7.8");
    }

    @Test
    void createCoursesList() {
        ArrayList<Course> resultCourses = null;
        JSONObject course = new JSONObject();
        JSONArray coursesList = new JSONArray();

        course.put("name", "test");
        course.put("code", "101");
        course.put("credit", 1);
        course.put("requiredCredits", 1);
        course.put("quota", 1);
        course.put("semester", 1);
        course.put("preRequisiteCourses", new JSONArray());
        course.put("weeklyHours", new JSONArray());

        coursesList.put(course);
        resultCourses = labelingControllerTest.createCoursesList(coursesList);
        assertEquals(resultCourses.size(), 1);
        assertEquals(resultCourses.get(0).getCode(), "101");

        JSONObject course2 = new JSONObject();
        course2.put("name", "test");
        course2.put("code", "102");
        course2.put("credit", 1);
        course2.put("requiredCredits", 1);
        course2.put("quota", 1);
        course2.put("semester", 1);
        course2.put("preRequisiteCourses", new JSONArray());
        course2.put("weeklyHours", new JSONArray());

        coursesList.put(course2);
        resultCourses = labelingControllerTest.createCoursesList(coursesList);
        assertEquals(resultCourses.size(), 2);
        assertEquals(resultCourses.get(0).getCode(), "101");
        assertEquals(resultCourses.get(1).getCode(), "102");
    }
}
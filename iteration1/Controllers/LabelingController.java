package iteration1.Controllers;

import iteration1.Models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LabelingController extends Controller {
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    private ArrayList<Curriculum> curriculums;
    private ArrayList<Course> courses;

    public void execute() {
        initObjects();
        SimulationController simulationController = new SimulationController(courses, students, curriculums, advisors);
        simulationController.setError(this.getError());
        simulationController.startSimulation();
    }

    public void initObjects() {
        initAdvisors();
        initCoursesAndCurriculum();
        initStudents();
    }

    public void initCoursesAndCurriculum() {
        courses = new ArrayList<>();
        curriculums = new ArrayList<>();

        ArrayList<JSONObject> objects = readJSONFiles("Curriculum");

        for (JSONObject object : objects) {
            Curriculum newCurriculum = new Curriculum();

            JSONArray courses = object.getJSONArray("Curriculum2020");
            for (int i = 0; i < courses.length(); i++) {
                JSONObject course = courses.getJSONObject(i);
                Course newCourse = new Mandatory(
                        course.getString("name"),
                        course.getString("code"),
                        course.getInt("credit"),
                        course.getInt("requiredCredits"),
                        course.getInt("quota"),
                        course.getInt("semester"),
                        createCoursesList(course.getJSONArray("preRequisiteCourses")),
                        createWeeklyHoursList(course.getJSONArray("weeklyHours")),
                        new ArrayList<>(),
                        null
                );
                newCurriculum.addCourseToSemester(newCourse);
                this.courses.add(newCourse);
            }
            curriculums.add(newCurriculum);
        }
    }

    private ArrayList<String> createWeeklyHoursList(JSONArray weeklyHours) {
        ArrayList<String> weeklyHoursList = new ArrayList<>();
        for (int i = 0; i < weeklyHours.length(); i++) {
            weeklyHoursList.add(weeklyHours.getString(i));
        }
        return weeklyHoursList;
    }


    public void initStudents() {
        students = new ArrayList<>();
        ArrayList<JSONObject> objects = readJSONFiles("Students");

        objects.forEach((n) -> {
                    Student newStudent = new Student(
                            n.getString("name"),
                            n.getString("surname"),
                            n.getString("ssn"),
                            n.getString("gender").charAt(0),
                            n.getString("id"),
                            n.getBoolean("isGraduate"),
                            n.getInt("registerDate"),
                            n.getInt("semesterNo"),
                            new Transcript(
                                    n.getJSONObject("Transcript").getDouble("gpa"),
                                    n.getJSONObject("Transcript").getInt("completedCredit"),
                                    createCoursesList(n.getJSONObject("Transcript").getJSONArray("completedCourses")),
                                    createCoursesList(n.getJSONObject("Transcript").getJSONArray("failedCourses"))
                            ),
                            chooseRandomAdvisor()
                    );
                    students.add(
                            newStudent
                    );

                    newStudent.getAdvisor().getStudents().add(newStudent);
                }
        );


        students.forEach((n) -> {
            n.setSelectedCourses(new HashMap<Course, Boolean>());
        });
    }

    private ArrayList<Course> createCoursesList(JSONArray coursesList) {
        ArrayList<Course> resultCourses = new ArrayList<>();
        JSONObject jsonObject = null;
        for (int i = 0; i < coursesList.length(); i++) {
            jsonObject = new JSONObject(coursesList.get(i).toString());
            JSONObject finalJsonObject = jsonObject;
            resultCourses.add(
                    this.courses.stream()
                            .filter(course -> course.getCode().equals(finalJsonObject.getString("code")))
                            .findFirst()
                            .orElse(new Mandatory(
                                    finalJsonObject.getString("name"),
                                    finalJsonObject.getString("code"),
                                    finalJsonObject.getInt("credit"),
                                    finalJsonObject.getInt("requiredCredits"),
                                    finalJsonObject.getInt("quota"),
                                    finalJsonObject.getInt("semester"),
                                    createCoursesList(finalJsonObject.getJSONArray("preRequisiteCourses")),
                                    createWeeklyHoursList(finalJsonObject.getJSONArray("weeklyHours")),
                                    new ArrayList<>(),
                                    null
                            ))
            );
        }

        return resultCourses;
    }

    public ArrayList<Advisor> initAdvisors() {
        advisors = new ArrayList<>();
        ArrayList<JSONObject> objects = readJSONFiles("Advisors");
        objects.forEach((n) -> advisors.add(new Advisor(
                new ArrayList<>(),
                n.getString("name"),
                n.getString("surname"),
                n.getString("ssn"),
                n.getString("gender").charAt(0),
                this.getError()
        )));

        return advisors;
    }

    private Advisor chooseRandomAdvisor() {
        int studentCount = students.size();
        for (Advisor advisor : advisors) {
            if (advisor.getStudents().size() <= studentCount / advisors.size()) {
                return advisor;
            }
        }

        return advisors.get(0);
    }

}
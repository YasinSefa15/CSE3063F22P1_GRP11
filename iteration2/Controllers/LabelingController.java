package iteration2.Controllers;

import iteration2.Models.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class LabelingController extends Controller {
    private ArrayList<Student> students;
    private ArrayList<Advisor> advisors;
    private ArrayList<Curriculum> curriculums;
    private ArrayList<Course> courses;


    public LabelingController(RegistrationError error) {
        super.setError(error);

    }

    private void initDirectories() {
        File directory = new File(System.getProperty("user.dir") + "/iteration2/Data/Input/Students");

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void execute() {
        //inits directories and objects first
        initDirectories();
        initObjects();

        //then start simulation
        SimulationController simulationController = new SimulationController(students, curriculums, advisors);
        simulationController.setError(this.getError());
        simulationController.startSimulation();
    }

    public void initObjects() {
        initAdvisors();
        initCoursesAndCurriculums();
        initStudents();
    }

    public void initStudents() {
        students = new ArrayList<>();
        RandomizationController randomizationController = new RandomizationController(new RegistrationError());
        students.addAll(randomizationController.generateStudentsAndExport(courses, advisors));
    }

    public void initCoursesAndCurriculums() {
        courses = new ArrayList<>();
        curriculums = new ArrayList<>();

        ArrayList<JSONObject> objects = readJSONFiles("Curriculum");

        for (JSONObject object : objects) {
            Curriculum curriculum2015 = new Curriculum();
            Curriculum curriculum2020 = new Curriculum();
            readCurriculumJSON(object, curriculum2015, "Curriculum2015");
            readCurriculumJSON(object, curriculum2020, "Curriculum2020");
            curriculums.add(curriculum2015);
            curriculums.add(curriculum2020);
        }
    }

    private void readCurriculumJSON(JSONObject object, Curriculum newCurriculum, String curriculum) {
        JSONArray courses = object.getJSONArray(curriculum);
        for (int i = 0; i < courses.length(); i++) {
            JSONObject course = courses.getJSONObject(i);
            switch (course.getInt("courseType")) {
                case 0:
                    Course mandatoryCourse = new Mandatory(
                            course.getString("name"),
                            course.getString("code"),
                            course.getInt("credit"),
                            course.getInt("requiredCredits"),
                            course.getInt("quota"),
                            course.getInt("semester"),
                            createCoursesList(course.getJSONArray("preRequisiteCourses")),
                            createWeeklyHoursList(course.getJSONArray("weeklyHours"))
                    );
                    newCurriculum.addCourseToSemester(mandatoryCourse);
                    this.courses.add(mandatoryCourse);
                    break;
                case 1:
                    Course labCourse = new Lab(
                            course.getString("name"),
                            course.getString("code"),
                            course.getInt("credit"),
                            course.getInt("requiredCredits"),
                            course.getInt("quota"),
                            course.getInt("semester"),
                            createCoursesList(course.getJSONArray("preRequisiteCourses")),
                            createWeeklyHoursList(course.getJSONArray("weeklyHours"))
                    );
                    newCurriculum.addCourseToSemester(labCourse);
                    this.courses.add(labCourse);
                    break;
                case 2:
                    Course electiveCourse = new Elective(
                            course.getString("name"),
                            course.getString("code"),
                            course.getInt("credit"),
                            course.getInt("requiredCredits"),
                            course.getInt("quota"),
                            course.getInt("semester"),
                            createCoursesList(course.getJSONArray("preRequisiteCourses")),
                            createWeeklyHoursList(course.getJSONArray("weeklyHours")),
                            course.getEnum(ElectiveType.class, "electiveType")
                    );
                    newCurriculum.addCourseToSemester(electiveCourse);
                    this.courses.add(electiveCourse);
                    break;
            }
        }
    }

    public ArrayList<String> createWeeklyHoursList(JSONArray weeklyHours) {
        ArrayList<String> weeklyHoursList = new ArrayList<>();
        for (int i = 0; i < weeklyHours.length(); i++) {
            weeklyHoursList.add(weeklyHours.getString(i));
        }
        return weeklyHoursList;
    }

    public ArrayList<Course> createCoursesList(JSONArray coursesList) {
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
                                    createWeeklyHoursList(finalJsonObject.getJSONArray("weeklyHours"))
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


    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }


    public ArrayList<Advisor> getAdvisors() {
        return advisors;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
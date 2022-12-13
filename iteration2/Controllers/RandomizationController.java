package iteration2.Controllers;

import iteration2.Models.*;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RandomizationController extends Controller {

    private final String[] firstNames = {"Yiğit", "Samet", "Baran", "Ali", "Emre", "Tahsin", "Necmettin", "Kerem", "Berke", "Hasan", "Osman", "Tunahan", "Yunus", "Yusuf", "Ceren", "Esra", "Kübra", "Ezgi", "Ayşe", "Berfin"};
    private final String[] lastNames = {"Ayan", "Öztürk", "Özdemir", "Aydın", "Demir", "Türkoğlu", "Yayın", "Oduncu", "Ekinci", "Toraman", "Akkurt", "Engin", "Yasan", "Özcan", "Kaya", "Kara", "Kılıç", "Koç"};

    public RandomizationController(RegistrationError error) {
        super.setError(error);
        try {
            File dir = new File(System.getProperty("user.dir") + "/iteration2/Data/Input/Students");
            for (File file : dir.listFiles())
                if (!file.isDirectory())
                    file.delete();

            dir = new File(System.getProperty("user.dir") + "/iteration2/Data/Output/Students");
            for (File file : dir.listFiles()) {
                file.delete();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public ArrayList<Student> generateStudentsAndExport(ArrayList<Course> courses, ArrayList<Advisor> advisors) {
        JSONObject parameters = null;
        try {
            Object obj = new JSONParser()
                    .parse(new FileReader(System.getProperty("user.dir") + "/iteration2/Data/Input/parameters.json"));
            parameters = new JSONObject(obj.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        int semester = parameters.getString("semester").equals("FALL") ? 1 : 2;
        int newStudentsCount = parameters.getInt("student_count");

        ArrayList<Student> students = generateStudents(newStudentsCount, courses, advisors, semester);
        students.forEach(this::exportJSONFile);
        return students;
    }

    public ArrayList<Student> generateStudents(int count, ArrayList<Course> courses, ArrayList<Advisor> advisors, int semester) {
        ArrayList<Student> students = new ArrayList<>();
        String ssn = "";
        int studentSemester = semester;

        for (int i = 0; i < count; i++) {
            studentSemester = semester + 2 * ((int) (Math.random() * 4));
            ssn = String.valueOf((long) (Math.random() * 1000000000));
            ssn += String.valueOf((int) (Math.random() * 100));
            Advisor advisor = chooseRandomAdvisor(students, advisors);
            students.add(new Student(
                    firstNames[(int) (Math.random() * firstNames.length)],
                    lastNames[(int) (Math.random() * lastNames.length)],
                    ssn,
                    (int) (Math.random() * 2) == 1 ? 'm' : 'f',
                    "1501" + String.valueOf(2022 - (semester / 2)).substring(1, 3).concat(String.valueOf((int) (Math.random() * 899 + 100))),
                    studentSemester == 4 && (int) (Math.random() * 2) == 1,
                    2022 - (studentSemester / 2),
                    studentSemester,
                    generateTranscript(studentSemester, courses),
                    advisor
            ));

            advisor.getStudents().add(students.get(students.size() - 1));

            students.forEach((n) -> {
                n.setSelectedCourses(new HashMap<Course, Boolean>());
            });

        }

        System.out.println("Generated new " + (count) + " students.");

        return students;
    }


    public Transcript generateTranscript(int semester, ArrayList<Course> courses) {
        Transcript transcript = new Transcript(
                0,
                0,
                new ArrayList<>(),
                new ArrayList<>()
        );

        boolean provides;

        for (Course course : courses) {
            if (course.getSemester() < semester) {
                //Student passed the course by 90% chance
                if ((int) (Math.random() * 6) >= 1) {
                    provides = true;
                    //student did not completed required credits for the course
                    if (transcript.getCompletedCredit() < course.getRequiredCredits()) {
                        provides = false;
                    } else {
                        //student has enough credits
                        //student did not completed required courses for the course
                        for (Course prerequisite : course.getPreRequisiteCourses()) {
                            if (!transcript.getCompletedCourses().contains(prerequisite)) {
                                provides = false;
                                break;
                            }
                        }
                    }

                    if (provides) {
                        transcript.addToCompletedCourses(course);
                        transcript.setCompletedCredit(transcript.getCompletedCredit() + course.getCredit());
                    }

                } else { //Student failed the course
                    transcript.addToFailedCourses(course);
                }
            }
        }

        transcript.calculateGPA();

        return transcript;
    }

    public Advisor chooseRandomAdvisor(ArrayList<Student> students, ArrayList<Advisor> advisors) {
        double studentCount = students.size();
        for (Advisor advisor : advisors) {
            if (advisor.getStudents().size() <= studentCount / advisors.size()) {
                return advisor;
            }
        }

        return advisors.get(0);
    }

    @Override
    public boolean exportJSONFile(Object object) {
        String content = (((Student) object).toJson()).toString();

        String fullFileName = System.getProperty("user.dir") + "/iteration2/Data/Input/Students/"
                + ((Student) object).getId() + ".json";

        try {
            File myObj = new File(fullFileName);
            myObj.createNewFile();

            FileWriter myWriter = new FileWriter(fullFileName);
            myWriter.write(content);
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred while exporting random .json file data.");
            e.printStackTrace();
        }

        return true;
    }
}
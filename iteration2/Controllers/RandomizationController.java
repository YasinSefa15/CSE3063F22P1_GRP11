package iteration2.Controllers;

import iteration2.Models.*;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class RandomizationController extends Controller {

    private final String[] firstNames = {"John", "Jane", "Bob", "Alice", "Joe", "Mary", "Tom", "Sally", "Bill", "Sue"};
    private final String[] lastNames = {"Smith", "Jones", "Brown", "Johnson", "Williams", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};

    public RandomizationController(RegistrationError error) {
        super.setError(error);
    }

    public ArrayList<Student> generateStudentsAndExport(ArrayList<Course> courses, ArrayList<Advisor> advisors) {
        String requestedPath = System.getProperty("user.dir") + "/iteration2/Data/Input/Students";
        String[] fileNames = new File(requestedPath).list();
        int availableStudents = fileNames.length;
        int newStudentsCount = (400 + (int) (Math.random() * 100)) - availableStudents;
        ArrayList<Student> students = generateStudents(newStudentsCount, courses, advisors);
        students.forEach(this::exportJSONFile);
        return students;
    }

    public ArrayList<Student> generateStudents(int count, ArrayList<Course> courses, ArrayList<Advisor> advisors) {
        ArrayList<Student> students = new ArrayList<>();
        int semester = 1;
        String ssn = "";


        for (int i = 0; i < count; i++) {
            if (i > count / 4 && i < count / 2) {
                semester = 2;
            } else if (i > count / 2 && i < count * 3 / 4) {
                semester = 3;
            } else if (i > count * 3 / 4) {
                semester = 4;
            }

            ssn = String.valueOf((long) (Math.random() * 1000000000));
            ssn += String.valueOf((int) (Math.random() * 100));
            students.add(new Student(
                    firstNames[(int) (Math.random() * firstNames.length)],
                    lastNames[(int) (Math.random() * lastNames.length)],
                    ssn,
                    (int) (Math.random() * 2) == 1 ? 'm' : 'f',
                    "1501" + String.valueOf(2022 - (semester / 2)).substring(1, 3).concat(String.valueOf((int) (Math.random() * 899 + 100))),
                    semester == 4 && (int) (Math.random() * 2) == 1,
                    2022 - (semester / 2),
                    semester,
                    generateTranscript(semester, courses),
                    advisors.get((int) (Math.random() * advisors.size()))
            ));

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

        boolean provides = true;

        for (Course course : courses) {
            if (course.getSemester() <= semester) {
                //Student passed the course
                if ((int) (Math.random() * 2) == 1) {
                    provides = true;
                    for (Course prerequisite : course.getPreRequisiteCourses()) {
                        if (!transcript.getCompletedCourses().contains(prerequisite)) {
                            provides = false;
                            break;
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
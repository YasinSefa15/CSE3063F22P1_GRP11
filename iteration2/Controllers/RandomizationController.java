package iteration2.Controllers;

import iteration2.Models.RegistrationError;
import iteration2.Models.Student;

import java.io.File;

public class RandomizationController extends Controller {

    private final String[] firstNames = {"John", "Jane", "Bob", "Alice", "Joe", "Mary", "Tom", "Sally", "Bill", "Sue"};
    private final String[] lastNames = {"Smith", "Jones", "Brown", "Johnson", "Williams", "Miller", "Davis", "Garcia", "Rodriguez", "Wilson"};

    public RandomizationController(RegistrationError error) {
        super.setError(error);
    }

    public void generateRandomInputs() {
        generateStudents(400 + (int) (Math.random() * 100));
    }

    public void generateStudents(int count) {
        String requestedPath = System.getProperty("user.dir") + "/iteration2/Data/Input/Students";
        String[] fileNames = new File(requestedPath).list();

        int availableStudents = fileNames.length;
        int semester = 1;
        String ssn = "";


        for (int i = 0; i < count - availableStudents; i++) {
            if (i > count / 4 && i < count / 2) {
                semester = 2;
            } else if (i > count / 2 && i < count * 3 / 4) {
                semester = 3;
            } else if (i > count * 3 / 4) {
                semester = 4;
            }

            ssn = String.valueOf((long) (Math.random() * 1000000000));
            ssn += String.valueOf((int) (Math.random() * 100));

            Student student = new Student(
                    firstNames[(int) (Math.random() * firstNames.length)],
                    lastNames[(int) (Math.random() * lastNames.length)],
                    ssn,
                    'm',
                    "x",
                    semester == 4 && (int) (Math.random() * 2) == 1,
                    2022 - (semester / 2),
                    semester,
                    null,
                    null
            );

        }

        System.out.println("Generated new " + (count - availableStudents) + " students." +
                " There has been " + availableStudents + " available students before the execution.");
    }

    @Override
    public boolean exportJSONFile(Object object) {
        return super.exportJSONFile(object);
    }
}
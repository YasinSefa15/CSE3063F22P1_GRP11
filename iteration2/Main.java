package iteration2;

import iteration2.Controllers.LabelingController;
import iteration2.Controllers.RandomizationController;
import iteration2.Models.RegistrationError;

public class Main {
    public static void main(String[] args) {
        RegistrationError error = new RegistrationError();
        RandomizationController randomizationController = new RandomizationController(new RegistrationError());
        randomizationController.generateRandomInputs();
        LabelingController labelingController = new LabelingController();
        labelingController.setError(error);
        labelingController.execute();
    }
}

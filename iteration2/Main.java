package iteration2;

import iteration2.Controllers.LabelingController;
import iteration2.Models.RegistrationError;

public class Main {
    public static void main(String[] args) {
        RegistrationError error = new RegistrationError();
        LabelingController labelingController = new LabelingController(error);
        labelingController.execute();
    }
}

package iteration1;

import iteration1.Controllers.LabelingController;
import iteration1.Models.RegistrationError;

public class Main {
    public static void main(String[] args) {
        RegistrationError error = new RegistrationError();
        LabelingController labelingController = new LabelingController();
        labelingController.setError(error);
        labelingController.execute();
    }
}

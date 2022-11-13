package iteration1.Controllers;

import iteration1.Models.RegistrationError;
import org.json.JSONObject;

import java.util.ArrayList;

public abstract class Controller {
    private RegistrationError error;

    public ArrayList<JSONObject> readJSONFiles(){
        return null;
    }
    public boolean exportJSONFile(Object object){
        return false;
    }

    public RegistrationError getError() {
        return error;
    }

    public void setError(RegistrationError error) {
        this.error = error;
    }
}

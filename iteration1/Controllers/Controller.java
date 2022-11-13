package iteration1.Controllers;

import iteration1.Models.RegistrationError;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public abstract class Controller {
    private RegistrationError error;

    public ArrayList<JSONObject> readJSONFiles(String path){
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();

        String requestedPath = System.getProperty("user.dir") + "/iteration1/Data/" + path;
        String[] fileNames = new File(requestedPath).list();

        for (String file : fileNames) {
            try {
                Object obj = new JSONParser().parse(new FileReader(requestedPath + "/" + file));
                JSONObject jo = new JSONObject(obj.toString()) ;
                jsonObjects.add(jo);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        return jsonObjects;
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

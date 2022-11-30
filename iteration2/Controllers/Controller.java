package iteration2.Controllers;

import iteration2.Models.*;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.ArrayList;

public abstract class Controller {
    private RegistrationError error;

    public ArrayList<JSONObject> readJSONFiles(String path) {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();

        String requestedPath = System.getProperty("user.dir") + "/iteration2/Data/Input/" + path;
        String[] fileNames = new File(requestedPath).list();

        for (String file : fileNames) {
            try {
                Object obj = new JSONParser().parse(new FileReader(requestedPath + "/" + file));
                JSONObject jo = new JSONObject(obj.toString());
                jsonObjects.add(jo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return jsonObjects;
    }

    public boolean exportJSONFile(Object object) {
        String fileName = "";
        String path = "";
        JSONObject jsonObject = null;
        String content = "";

        switch (object.getClass().getSimpleName()) {
            case "Student":
                fileName = ((Student) object).getId();
                jsonObject = ((Student) object).toJson();
                path = "Output/Students";
                content = jsonObject.toString();
                break;
            case "Advisor":
                fileName = (((Advisor) object).getName() + ((Advisor) object).getSurname()).replace(" ", "");
                jsonObject = ((Advisor) object).toJson();
                path = "Output/Advisors";
                content = jsonObject.toString();
                break;
            case "RegistrationError":
                fileName = "RegistrationErrors";
                jsonObject = ((RegistrationError) object).toJson();
                content = jsonObject.toString();
                break;
            default:
                return false;
        }
        String fullFileName = System.getProperty("user.dir") + "/iteration2/Data/" + path + "/" + fileName + ".json";

        try {
            File directory = new File(String.valueOf(System.getProperty("user.dir") + "/iteration2/Data/" + path));

            if (!directory.exists()) {
                directory.mkdirs();
            }
            //System.exit(1);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File myObj = new File(fullFileName);
            myObj.createNewFile();

            FileWriter myWriter = new FileWriter(fullFileName);
            myWriter.write(content);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while exporting .json file.");
            e.printStackTrace();
        }
        return false;
    }

    public RegistrationError getError() {
        return error;
    }

    public void setError(RegistrationError error) {
        this.error = error;
    }
}

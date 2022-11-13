package iteration1.Models;

import org.json.JSONObject;

import java.util.HashMap;

public class RegistrationError extends Model {

    private HashMap<Integer,String> errorType;
    private HashMap<Integer,String[]> errorInfo;

    public String reportError(int errorCode, String[] data ) {

        String s="";
        return s;
    }

    public void updateErrorInfo(int errorCode, String[] data ) {

    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

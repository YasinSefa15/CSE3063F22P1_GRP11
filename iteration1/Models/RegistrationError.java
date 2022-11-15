package iteration1.Models;

import org.json.JSONObject;

import java.util.HashMap;

public class RegistrationError extends Model {

    private HashMap<Integer,String> errorType;
    private HashMap<Integer,String[]> errorInfo;

    public String reportError(int errorCode, String[] data ) {
        String message="";
        switch (errorCode){
            case 1001:  //Semestr error     data[]= "CSE2225 ","4", "2"
                message="The advisor didn't approve "+data[0] +" because course's semestr is "+data[1]+ "while student's is " +data[2];
                break;

            case 1002: //credit error       data[]= "TE CSE4062","<155";
                message="The advisor didn't approve " + data[0]+ " because student completed credits " +data[1]; //<155 tarzında
                break;

            case 1003:  //Quota error       data[]= "CSE2023";
                message="The advisor didn't approve "+data[0]+" because course's quota is full";
                break;

            case 1004: //prereq error       data[]= "CSE2225 ","CSE1242";
                message="The system didn't allow "+ data[0]+ " because student failed prereq: " + data[1];
                break;

            case 1005:  //collision error       data[]= "CSE3062 ","CSE2025", "2"
                message="Advisor didn't approve " +data[0] + " because of " +data[2]+ " hours collision with "+ data[1] + " in schedule.";
                break;

            case 1006:  //Elective course error     data[]= "TE CSE4062","2 TE", "155"
                message="The advisor didn't approve " + data[0] + " because student already took " +data[1]+ " and in FALL semester only 2 TE can be taken";
                break;

            default:
                message="Undefined error!";
        }
        return message;
    }

    public void updateErrorInfo(int errorCode, String[] data ) {

    }

    @Override
    public JSONObject toJson() {

        return null;
    }
}

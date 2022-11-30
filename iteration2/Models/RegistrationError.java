package iteration2.Models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class RegistrationError extends Model {

    private HashMap<Integer, String> errorType=new HashMap<>();
    private ArrayList<String> errorList = new ArrayList<String>();
    private ArrayList<String> allErrorMessages = new ArrayList<>();
    private int noOfLastErrorType = 1006;

    public RegistrationError(){
        errorType.put(1001, "semester inconsistency");
        errorType.put(1002, "lack of credits");
        errorType.put(1003, "lack of quota");
        errorType.put(1004, "did not meet the prerequisite");
        errorType.put(1005, "course collision");
        errorType.put(1006, "elective error");
    }
    public String reportError(int errorCode, String[] data) {
        String message = "";

        switch (errorCode) {
            case 1001:  //Semester error     data[]= "CSE2225 ","4", "2"
                message = "The advisor didn't approve " + data[0] + " because course's semester is " + data[1] + " while student's is " + data[2];
                storeErrorInfo(1001,data[0]);
                break;

            case 1002: //credit error       data[]= "TE CSE4062","<155";
                message = "The advisor didn't approve " + data[0] + " because student completed credits " + data[1]; //<155 tarzında
                storeErrorInfo(1002,data[0]);
                break;

            case 1003:  //Quota error       data[]= "CSE2023";
                message = "The advisor didn't approve " + data[0] + " because course's quota is full";
                storeErrorInfo(1003,data[0]);
                break;

            case 1004: //prereq error       data[]= "CSE2225 ","CSE1242";
                message = "The system didn't allow " + data[0] + " because student failed prereq: " + data[1];
                storeErrorInfo(1004,data[0]);
                break;

            case 1005:  //collision error       data[]= "CSE3062 ","CSE2025", "2"
                message = "Advisor didn't approve " + data[0] + " because of " + data[2] + " hours collision with " + data[1] + " in schedule.";
                storeErrorInfo(1005,data[0]);
                break;

            case 1006:  //Elective course error     data[]= "TE CSE4062","2 TE", "155"
                message = "The advisor didn't approve " + data[0] + " because student already took " + data[1] + " and in FALL semester only 2 TE can be taken";
                storeErrorInfo(1006,data[0]);
                break;
            default:
                message = "Undefined error!";
        }
        return message;
    }

    public void storeErrorInfo(Integer errorCode, String data) {
        String temp = errorCode.toString() + "," + data;
        errorList.add(temp);
    }

    public void writeAllErrors(ArrayList<String> list) {
        int i;
        for (i =0; i < (noOfLastErrorType - 1000); i++) {
            ArrayList<String> courseList = new ArrayList<String>();
            for (int j = 0; j < list.size(); j++) {
                String[] parts = list.get(j).split(",");
                Integer a = (i + 1001);
                if (Objects.equals(parts[0], a.toString())) {
                    courseList.add(parts[1]);
                }
            }
            Set<String> s = new LinkedHashSet<>(courseList);    //1001 : "CSE2023", "CSE3033", "CSE4040"
            ArrayList<String> temp =new ArrayList<>(s);
            writeNumberOfErrors(temp,courseList,(i+1001));
            courseList.clear();
        }
    }

    private void writeNumberOfErrors(ArrayList<String> list, ArrayList<String> courseList, int errorCode) {
        int count=0;
        String currentCourse="";
        for (int i = 0; i < list.size(); i++) {
            currentCourse = list.get(i);
            for(int j = 0; j<courseList.size(); j++){
                if (Objects.equals(courseList.get(j), currentCourse)){
                    count++;
                }
            }
            if(count!=0) {
                String message = count + " students could not register " + currentCourse + " because of " + errorType.get(errorCode) + "!";
                allErrorMessages.add(message);
            }
            count=0;
        }
    }
    @Override
    public JSONObject toJson() {
        writeAllErrors(errorList);
        JSONObject jsonObject =new JSONObject();
        JSONArray jsonArray = new JSONArray(allErrorMessages);
        jsonObject.put("totalErrors",jsonArray);
        return jsonObject;
    }
}

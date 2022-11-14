package iteration1.Models;
import org.json.JSONObject;

import java.util.ArrayList;

public class Mandatory extends Course {
    private ArrayList<Lab> lab = new ArrayList<>();

    public Mandatory(){}
    public void assignQuota(){

    }
    public ArrayList<Lab> getLab() {
        return lab;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

package iteration2.Models;

import org.json.JSONObject;

public class Lab extends Course {

    private int sectionNumber;
    private Course course;

    public Lab(){}
    public Lab(Course course){
        this.course = course;
        generateLabCode();
    }

    public void generateLabCode(){

    }
    public int getSectionNumber() {
        return sectionNumber;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }
}

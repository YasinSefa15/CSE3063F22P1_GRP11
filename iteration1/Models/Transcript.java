package iteration1.Models;

import org.json.JSONObject;

import java.util.ArrayList;

public class Transcript extends Model{
    private double gpa;
    private int completedCredit;
    private ArrayList<Course> completedCourses;
    private  ArrayList<Course> failedCourses;

    public void  calculateGPA(){
        double tempGpa=0;
        for (Course course:completedCourses){
            double randomScore=Math.random()*4;
            tempGpa+=course.getCredit()*randomScore;
        }
        this.gpa=tempGpa/completedCredit;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa >= 0) {
            this.gpa = gpa;
        }
    }

    public int getCompletedCredit() {
        return completedCredit;
    }

    public void setCompletedCredit(int completedCredit) {
        if (completedCredit>=0){
            this.completedCredit = completedCredit;
        }
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        for (int i=0; i<completedCourses.size();i++){
            for (int j = 0; j<this.completedCourses.size();j++){
                if (completedCourses.get(i).getCode()!=this.completedCourses.get(j).getCode()){
                    this.completedCourses.add(completedCourses.get(i));
                }
            }
        }
    }

    public ArrayList<Course> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<Course> failedCourses) {
        for (int i=0; i<failedCourses.size();i++){
            for (int j = 0; j<this.failedCourses.size();j++){
                if (failedCourses.get(i).getCode()!=this.failedCourses.get(j).getCode()){
                    this.failedCourses.add(failedCourses.get(i));
                }
            }
        }
    }


    @Override
    public JSONObject toJson() {
        return null;
    }
}

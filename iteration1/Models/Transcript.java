package iteration1.Models;

public class Transcript {
    private double gpa;
    private int completedCredit;
    private ArrayList<Course> completedCourses;
    private  ArrayList<Course> failedCourses;

    public void  calculateGPA(){
        double tempGpa=0;
        for (Course course:completedCourses){
            double randomScore=Math.random()*4;
            tempGpa+=course.credit*randomScore;
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
        this.completedCredit = completedCredit;
    }

    public ArrayList<Course> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(ArrayList<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public ArrayList<Course> getFailedCourses() {
        return failedCourses;
    }

    public void setFailedCourses(ArrayList<Course> failedCourses) {
        this.failedCourses = failedCourses;
    }
}

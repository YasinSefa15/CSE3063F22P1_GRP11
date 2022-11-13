package iteration1.Models;

public class Lab {

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
}

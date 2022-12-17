package iteration2.Models;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Transcript extends Model{
    private double gpa;
    private int completedCredit;
    private ArrayList<Course> completedCourses;
    private  ArrayList<Course> failedCourses;

    private static final String FILE_PATH="/iteration2/Logs/Transcript.log";
    private static Logger logger=Logger.getLogger(Transcript.class.getName());
    private static FileHandler fileHandler;

    static {
        try {
            fileHandler = new FileHandler(System.getProperty("user.dir")+FILE_PATH,true);
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void customLog(boolean type,String message){
        SimpleFormatter formatter =new SimpleFormatter();
        fileHandler.setFormatter(formatter);
        if(type)
            logger.info(message);
        else
            logger.warning(message);
    }

    public Transcript(double gpa, int completedCredit, ArrayList<Course> completedCourses, ArrayList<Course> failedCourses) {
        this.gpa = gpa;
        this.completedCredit = completedCredit;
        this.completedCourses = completedCourses;
        this.failedCourses = failedCourses;
        customLog(true,"Transcript constructor method is called and new transcript object is generated.");
    }

    public void  calculateGPA(){
        double tempGpa=0;
        int totalCredit=0;
        int courseCredit=0;
        for (Course course:completedCourses){
            courseCredit=course.getCredit();
            totalCredit+=courseCredit;
            tempGpa+=courseCredit*4;
        }
        for (Course course:failedCourses){
            courseCredit=course.getCredit();
            totalCredit+=courseCredit;
            tempGpa+=courseCredit*0;
        }
        this.gpa=tempGpa/totalCredit;
        customLog(true,"The student's GPA is calculated.");
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

    public void addToCompletedCourses(Course course){
        if (!completedCourses.contains(course)){
            completedCourses.add(course);
            customLog(true,"A new course has been added to the courses that the student has completed.");
        }else {
            customLog(false,"A new course could not be added to the courses that the student completed" +
                    " because it has already completed it.");
        }
    }

    public void addToFailedCourses(Course course){
        if (!failedCourses.contains(course)){
            failedCourses.add(course);
            customLog(true,"A new course has been added to the courses that the student has failed.");
        }else {
            customLog(false,"A new course could not be added to the courses that the student failed because it has already failed it.");
        }
    }

    public JSONArray convertArraylistToJsonArray(ArrayList<Course> arrayList){
        JSONArray jsonArray=new JSONArray();
        for (int i=0;i<arrayList.size();i++){
            jsonArray.put(arrayList.get(i).toJson());
        }
        return jsonArray;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("gpa",new DecimalFormat("##.##").format(gpa));
        jsonObject.put("completedCredit",completedCredit);
        jsonObject.put("completedCourses",convertArraylistToJsonArray(completedCourses));
        jsonObject.put("failedCourses",convertArraylistToJsonArray(failedCourses));
        return  jsonObject;
    }
}

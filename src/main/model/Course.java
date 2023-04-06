// Represents a single course in a student's list of courses

package model;

import events.Event;
import events.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

public class Course implements Writable {
    private String courseName;
    private String subject;
    private String teacher;
    private double courseGrade;
    private List<Weighting> weightingScheme;

    //EFFECTS: creates a new course with a name and subject
    public Course(String courseName, String subject) {
        this.courseName = courseName;
        this.subject = subject;
        this.courseGrade = 100;
        weightingScheme = new ArrayList<>();
    }

    // EFFECTS: creates a new course with a name, subject, teacher, and grade
    public Course(String courseName, String subject, String teacher, double courseGrade) {
        this.courseName = courseName;
        this.subject = subject;
        this.teacher = teacher;
        this.courseGrade = courseGrade;
        weightingScheme = new ArrayList<>();
    }

    //EFFECTS: creates a new course with a name, subject, and grade
    public Course(String courseName, String subject, double courseGrade) {
        this.courseName = courseName;
        this.subject = subject;
        this.courseGrade = courseGrade;
        weightingScheme = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a mark with category to given category's weighting, and updates course grade automatically
    public void addMarkEntry(MarkEntry markEntry) {
        double total = 100;
        double num = 0;

        for (Weighting weight : weightingScheme) {
            if (markEntry.getCategory().equals(weight.getCategory())) {
                weight.addMarkEntry(markEntry);
                EventLog.getInstance().logEvent(new Event("A mark of " + markEntry.getMark() + "% has been added to "
                        + weight.getCategory() + " of " + courseName));
            }

            if (weight.getMarksList().size() == 0) {
                total -= weight.getWeight();
            }
            num += weight.calculateWeightedMark();
        }
        this.courseGrade = (num / total * 100);
    }

    //Getters
    public String getCourseName() {
        return this.courseName;
    }

    public String getSubject() {
        return this.subject;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public double getCourseGrade() {
        return this.courseGrade;
    }

    public List<Weighting> getWeightingScheme() {
        return this.weightingScheme;
    }

    //Setters
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setWeightingScheme(List<Weighting> weightingList) {
        this.weightingScheme = weightingList;
    }

    // EFFECTS: creates a course JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("course name", courseName);
        json.put("subject", subject);
        json.put("teacher", teacher);
        json.put("course grade", courseGrade);
        json.put("weightings", weightingsToJson());
        return json;
    }

    // EFFECTS: puts weightings into course JSON object
    private JSONArray weightingsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Weighting w : weightingScheme) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;

    }
}

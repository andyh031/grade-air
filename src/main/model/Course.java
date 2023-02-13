// Represents a single course in a student's list of courses

package model;

import java.util.*;

public class Course {
    private String courseName;
    private String subject;
    private String teacher;
    private String gradeTaken;      // Grade 12, Grade 6, First Year University
    private int courseGrade;
    private List<Weighting> weightingScheme;

    //EFFECTS: creates a new course with a name and subject
    public Course(String courseName, String subject) {
        this.courseName = courseName;
        this.subject = subject;
        weightingScheme = new ArrayList<>();
        this.courseGrade = 100;
    }

    //MODIFIES: this
    //EFFECTS: adds a mark with category to given category's weighting, and updates course grade automatically
    public void addMarkEntry(String name, int mark, String category) {
        MarkEntry markEntry = new MarkEntry(name, mark, category);
        double total = 100;
        double num = 0;

        for (Weighting weight : weightingScheme) {
            if (category.equals(weight.getCategory())) {
                weight.addMarkEntry(markEntry);
            }

            if (weight.getMarksList().size() == 0) {
                total -= weight.getWeight();
            }
            num += weight.calculateWeightedMark();
        }
        this.courseGrade = (int)(num / total * 100);
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

    public String getGradeTaken() {
        return this.gradeTaken;
    }

    public int getCourseGrade() {
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

    public void setGradeTaken(String gradeTaken) {
        this.gradeTaken = gradeTaken;
    }
}

package model;

import java.util.*;

public class Course {
    private String courseName;
    private String subject;
    private String teacher;
    private String gradeTaken;      // Grade 12, Grade 6, First Year University
    private Scanner scanner;
    private List<MarkEntry> markEntryList;
    private double courseGrade;
    private List<SingleWeighting> courseWeighting;
    private HashMap<String, Integer> courseMap;
    private int grade;

    //EFFECTS: creates a new course with a name and subject
    public Course(String courseName, String subject) {
        this.courseName = courseName;
        this.subject = subject;
        markEntryList = new LinkedList<>();
        courseWeighting = new ArrayList<>();
        courseMap = new HashMap<>();
    }

    public void addMark() {
        MarkEntry mark = new MarkEntry();
        markEntryList.add(mark);

        Integer count = courseMap.containsKey(mark.getCategory()) ? courseMap.get(mark.getCategory()) : 1;
        courseMap.put(mark.getCategory(), count + 1);
        calculateCourseGrade();
    }

    public void calculateCourseGrade() {
        float grade = 0;
        for (MarkEntry markEntry : markEntryList) {
            float mark = markEntry.getMark() / (float)100;
            String markCategory = markEntry.getCategory();
            int weightOfCategory = 0;
            float weightOfMark;
            float weightedMark;

            for (SingleWeighting singleWeighting : courseWeighting) {
                if (markCategory.equals(singleWeighting.getCategory())) {
                    weightOfCategory = singleWeighting.getWeight();
                }
            }

            weightOfMark = weightOfCategory / courseMap.get(markCategory);
            weightedMark = mark * weightOfMark;
            grade += weightedMark;
        }
        this.grade = (int)grade;
    }

    public void initializeWeightings() {
        int totalWeightSoFar = 0;
        do {
            courseWeighting.add(new SingleWeighting());
            courseMap.put(courseWeighting.get(courseWeighting.size() - 1).getCategory(), 0);
            totalWeightSoFar += courseWeighting.get(courseWeighting.size() - 1).getWeight();
        } while (!(totalWeightSoFar == 100));
        calculateCourseGrade();
    }

    public String makePrettyUpperCase(String str) {
        str = str.toUpperCase();
        str = str.trim();
        return str;
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

    public List<MarkEntry> getMarkEntryList() {
        return this.markEntryList;
    }

    public int getGrade() {
        return this.grade;
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

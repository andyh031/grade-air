package model;

import java.util.*;

public class Student {

    private List<Course> courses;
    private String firstName;
    private String lastName;

    //EFFECTS: Constructs a student with no courses and empty fields
    public Student(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
        this.courses = new ArrayList<>();
    }

    public int calculateGPA() {
        int total = 0;
        for (Course course : courses) {
            total += course.getCourseGrade();
        }
        if (courses.size() == 0) {
            return 100;
        } else {
            return total / courses.size();
        }
    }

    public void sortCoursesAlphabetical() {
        Collections.sort(courses, Comparator.comparing(Course::getCourseName));
    }

    public void sortCoursesByGrade() {
        Collections.sort(courses, Comparator.comparing(Course::getCourseGrade));
    }

    //MODIFIES: this
    //EFFECTS: adds a course to student's list of courses
    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    //Getters
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    //Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

// Represents a student class with a first name, last name, and list of courses. A student can add a course onto
// a list of their courses. For each course, they are required to assign a weighting scheme to it, which is
// comprised of a list of single weightings. In each of these weightings, there is also a list of mark entries,

package model;

import events.Event;
import events.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.*;

public class Student implements Writable {
    private List<Course> courses;
    private String firstName;
    private String lastName;

    //EFFECTS: Constructs a student with first name, last name, and no courses
    public Student(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
        this.courses = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a course to student's list of courses
    public void addCourse(Course course) {
        courses.add(course);
        EventLog.getInstance().logEvent(new Event("A new course has been added: " + course.getCourseName()));
    }

    //MODIFIES: this
    //EFFECTS: takes in a course and removes it
    public void removeCourse(Course course) {
        courses.remove(course);
        EventLog.getInstance().logEvent(new Event("A course has been removed: " + course.getCourseName()));
    }

    //MODIFIES: this
    //EFFECTS: removes course from student's list of courses
    public void removeCourseByName(String name) {
        for (Course course : courses) {
            if (name.equals(course.getCourseName())) {
                courses.remove(course);
                break;
            }
        }
        EventLog.getInstance().logEvent(new Event("A course has been removed: " + name));
    }

    // EFFECTS: returns a calculation of the student's GPA over all courses
    public double calculateGPA() {
        double total = 0;
        for (Course course : courses) {
            total += course.getCourseGrade();
        }
        if (courses.size() == 0) {
            return 100;
        } else {
            return total / courses.size();
        }
    }

    //MODIFIES: this
    //EFFECTS: sorts the courses in alphabetical order (a -> z)
    public void sortCoursesAlphabetical() {
        courses.sort(Comparator.comparing(Course::getCourseName));
    }

    //MODIFIES: this
    //EFFECTS: sorts the courses by grade achieved (highest -> lowest)
    public void sortCoursesByGrade() {
        courses.sort(Comparator.comparing(Course::getCourseGrade));
        Collections.reverse(courses);
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
        EventLog.getInstance().logEvent(new Event("Student first name changed to: " + firstName));
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        EventLog.getInstance().logEvent(new Event("Student last name changed to: " + lastName));
    }

    // EFFECTS: creates a student JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("first name", firstName);
        json.put("last name", lastName);
        json.put("courses", coursesToJson());
        return json;
    }

    // EFFECTS: puts courses into student JSON object
    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course course : courses) {
            jsonArray.put(course.toJson());
        }

        return jsonArray;
    }
}

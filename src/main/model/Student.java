// Represents a student class with a first name, last name, and list of courses. A student can add a course onto
// a list of their courses. For each course, they are required to assign a weighting scheme to it, which is
// comprised of a list of single weightings. In each of these weightings, there is also a list of mark entries,

package model;

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
    }

    //MODIFIES: this
    //EFFECTS: removes course from student's list of courses
    public void removeCourse(String name) {
        for (Course course : courses) {
            if (name.equals(course.getCourseName())) {
                courses.remove(course);
                break;
            }
        }
    }

    // EFFECTS: returns a calculation of the student's GPA over all courses
    public double calculateGPA() {
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

    //MODIFIES: this
    //EFFECTS: sorts the courses in alphabetical order (a -> z)
    public void sortCoursesAlphabetical() {
        Collections.sort(courses, Comparator.comparing(Course::getCourseName));
    }

    //MODIFIES: this
    //EFFECTS: sorts the courses by grade achieved (highest -> lowest)
    public void sortCoursesByGrade() {
        Collections.sort(courses, Comparator.comparing(Course::getCourseGrade));
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
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("first name", firstName);
        json.put("last name", lastName);
        json.put("courses", coursesToJson());
        return json;
    }

    private JSONArray coursesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Course course : courses) {
            jsonArray.put(course.toJson());
        }

        return jsonArray;
    }
}

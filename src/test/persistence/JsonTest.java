package persistence;

import model.Course;
import model.MarkEntry;
import model.Weighting;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String courseName, String teacher, String subject, double courseGrade, Course course) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(teacher, course.getTeacher());
        assertEquals(subject, course.getSubject());
        assertEquals(courseGrade, course.getCourseGrade());
    }

    protected void checkWeighting(String category, int weight, Weighting weighting) {
        assertEquals(category, weighting.getCategory());
        assertEquals(weight, weighting.getWeight());
    }

    protected void checkMark(String name, String category, double mark, MarkEntry markEntry) {
        assertEquals(name, markEntry.getName());
        assertEquals(category, markEntry.getCategory());
        assertEquals(mark, markEntry.getMark());
    }

    protected void checkCourse(String courseName, String subject, double courseGrade, Course course) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(subject, course.getSubject());
        assertEquals(courseGrade, course.getCourseGrade());
    }

    protected void checkCourse(String courseName, String subject, Course course) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(subject, course.getSubject());
    }
}

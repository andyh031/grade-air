package persistence;

import model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCourse(String courseName, String teacher, String subject, double courseGrade, Course course) {
        assertEquals(courseName, course.getCourseName());
        assertEquals(teacher, course.getTeacher());
        assertEquals(subject, course.getSubject());
        assertEquals(courseGrade, course.getCourseGrade());
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

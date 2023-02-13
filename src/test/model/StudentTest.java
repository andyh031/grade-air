package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    private Student student;
    private Course course1;
    private Course course2;

    @BeforeEach
    public void setup() {
        student = new Student("A", "B");
        course1 = new Course("A", "A");
        course2 = new Course("B", "B");
    }

    @Test
    public void testConstructor() {
        assertEquals("A", student.getFirstName());
        assertEquals("B", student.getLastName());
        assertEquals(0, student.getCourses().size());
    }

    @Test
    public void testAddCourseAndRemoveCourse() {
        student.addCourse(course1);
        assertEquals(1, student.getCourses().size());
        assertEquals(course1, student.getCourses().get(0));
        student.addCourse(course2);
        assertEquals(2, student.getCourses().size());
        assertEquals(course2, student.getCourses().get(1));
        student.removeCourse("A");
        assertEquals(1, student.getCourses().size());
        student.removeCourse("B");
        assertEquals(0, student.getCourses().size());

    }

    @Test
    public void testCalculateGPA() {
        assertEquals(100, student.calculateGPA());
        student.addCourse(course1);
        assertEquals(100, student.calculateGPA());
    }


}

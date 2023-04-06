package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest extends CourseTest {
    protected Student student;

    @BeforeEach
    public void setup3() {
        student = new Student("Andy", "Hu");
    }

    @Test
    public void testStudentConstructor() {
        assertEquals("Andy", student.getFirstName());
        assertEquals("Hu", student.getLastName());
        assertEquals(0, student.getCourses().size());
    }

    @Test
    public void testRemoveCourseWithCourse() {
        student.addCourse(course1);
        student.removeCourse(course1);
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

        student.removeCourseByName("CPSC 110");
        assertEquals(1, student.getCourses().size());

        student.removeCourseByName("A");
        assertEquals(1, student.getCourses().size());

        student.removeCourseByName("CPSC 210");
        assertEquals(0, student.getCourses().size());

        student.removeCourseByName("A");
        assertEquals(0, student.getCourses().size());
    }

    @Test
    public void testCalculateGPANoClasses() {
        assertEquals(100, student.calculateGPA());
        student.addCourse(course1);
        assertEquals(100, student.calculateGPA());
    }

    @Test
    public void testCalculateGPA() {
        student.addCourse(course1);
        student.addCourse(course2);
        course1.getWeightingScheme().add(allWeight);
        course2.getWeightingScheme().add(allWeight);

        course1.addMarkEntry(all1);
        course2.addMarkEntry(all2);

        assertEquals((course1.getCourseGrade() + course2.getCourseGrade()) / 2, student.calculateGPA());
    }

    @Test
    public void testSortAlphabetical() {
        student.addCourse(course2);
        student.addCourse(course1);
        assertEquals(course2, student.getCourses().get(0));
        assertEquals(course1, student.getCourses().get(1));

        student.sortCoursesAlphabetical();
        assertEquals(course1, student.getCourses().get(0));
        assertEquals(course2, student.getCourses().get(1));
    }

    @Test
    public void testSortGrade() {
        student.addCourse(course1);
        student.addCourse(course2);
        course1.getWeightingScheme().add(allWeight);
        course2.getWeightingScheme().add(allWeight);
        assertEquals(course1, student.getCourses().get(0));
        assertEquals(course2, student.getCourses().get(1));

        course2.addMarkEntry(all1);
        course1.addMarkEntry(all2);
        student.sortCoursesByGrade();

        assertEquals(course2, student.getCourses().get(0));
        assertEquals(course1, student.getCourses().get(1));
    }

    @Test
    public void testSettersGetters() {
        student.setFirstName("Bob");
        student.setLastName("Li");

        assertEquals("Bob", student.getFirstName());
        assertEquals("Li", student.getLastName());
    }

}

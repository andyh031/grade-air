package persistence;

import model.Course;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistent.json");
        try {
            Student student = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testReaderStudentEmptyCourses() {
        JsonReader reader = new JsonReader("./data/testReaderStudentEmptyCourses.json");
        try {
            Student student = reader.read();
            assertEquals("Andy", student.getFirstName());
            assertEquals("Hu", student.getLastName());
            assertEquals(0, student.getCourses().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralStudent() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralStudent.json");
        try {
            Student student = reader.read();
            assertEquals("abc", student.getLastName());
            assertEquals("xyz", student.getFirstName());
            List<Course> courses = student.getCourses();
            assertEquals(2, courses.size());
            checkCourse("A", "MR. A", "BIOLOGY", 95, courses.get(0));
            checkCourse("PHYS 106", "PHYSICS", 100, courses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

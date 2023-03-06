package persistence;

import model.Course;
import model.Student;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Student student = new Student("Andy", "Hu");
            JsonWriter writer = new JsonWriter("./data/InvalidName\0:.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {

        }
    }

    @Test
    void testWriterStudentEmptyCourses() {
        try {
            Student student = new Student("A", "B");
            JsonWriter writer = new JsonWriter("./data/testWriterStudentEmptyCourses.json");
            writer.open();
            writer.write(student);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStudentEmptyCourses.json");
            student = reader.read();
            assertEquals("A", student.getFirstName());
            assertEquals("B", student.getLastName());
            assertEquals(0, student.getCourses().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralStudent() {
        try {
            Student student = new Student("A", "B");
            student.addCourse(new Course("BIOL 121", "BIOLOGY", "MR. A", 90));
            student.addCourse(new Course("PHYS 106", "PHYSICS"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralStudent.json");
            writer.open();
            writer.write(student);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralStudent.json");
            student = reader.read();
            assertEquals("A", student.getFirstName());
            assertEquals("B", student.getLastName());
            List<Course> courses = student.getCourses();
            assertEquals(2, courses.size());
            checkCourse("BIOL 121", "MR. A","BIOLOGY", 90, courses.get(0));
            checkCourse("PHYS 106", "PHYSICS", courses.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

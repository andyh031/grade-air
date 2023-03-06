// Data persistence so that users can save their progress, by having a file reader that can extract information.

package persistence;

import model.Course;
import model.MarkEntry;
import model.Student;
import model.Weighting;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs student from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads student from file and returns it,
    //          throws IOException if error occurs reading data from file
    public Student read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStudent(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses student from JSON object and returns it
    private Student parseStudent(JSONObject jsonObject) {
        String fname = jsonObject.getString("first name");
        String lname = jsonObject.getString("last name");
        Student student = new Student(fname, lname);
        addCourses(student, jsonObject);
        return student;
    }

    // MODIFIES: student
    // EFFECTS: parses courses from JSON object and adds them to student
    private void addCourses(Student student, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("courses");
        for (Object json : jsonArray) {
            JSONObject nextCourse = (JSONObject) json;
            addCourse(student, nextCourse);
        }
    }

    // MODIFIES: student
    // EFFECTS: parses course from JSON object and adds it to student
    private void addCourse(Student student, JSONObject jsonObject) {
        String courseName = jsonObject.getString("course name");
        String subject = jsonObject.getString("subject");
        double courseGrade = jsonObject.getDouble("course grade");

        if (jsonObject.has("teacher")) {
            String teacher = jsonObject.getString("teacher");
            Course course = new Course(courseName, subject, teacher, courseGrade);
            addWeightings(course, jsonObject);
            student.getCourses().add(course);
        } else {
            Course course = new Course(courseName, subject, courseGrade);
            addWeightings(course, jsonObject);
            student.getCourses().add(course);
        }
    }

    //MODIFIES: course
    //EFFECTS: parses weightings from JSON object and adds them to course
    private void addWeightings(Course course, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("weightings");
        for (Object json : jsonArray) {
            JSONObject nextWeighting = (JSONObject) json;
            addWeighting(course, nextWeighting);
        }
    }

    //MODIFIES: course
    //EFFECTS: parses weighting from JSON object and adds it to course
    private void addWeighting(Course course, JSONObject jsonObject) {
        String category = jsonObject.getString("category");
        int weight = jsonObject.getInt("weight");

        Weighting weighting = new Weighting(category, weight);
        addMarkEntries(weighting, jsonObject);
        course.getWeightingScheme().add(weighting);
    }

    //MODIFIES: weighting
    //EFFECTS: parses mark entries from JSON object and adds them to weighting
    private void addMarkEntries(Weighting weighting, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("marks");
        for (Object json : jsonArray) {
            JSONObject nextMark = (JSONObject) json;
            addMark(weighting, nextMark);
        }
    }

    //MODIFIES: course
    //EFFECTS: parses mark entry from JSON object and adds it to course
    private void addMark(Weighting weight, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String category = jsonObject.getString("category");
        double mark = jsonObject.getDouble("mark");

        MarkEntry markEntry = new MarkEntry(name, mark, category);
        weight.getMarksList().add(markEntry);
    }
}

package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest extends Variables {

    @Test
    public void testConstructor() {
        assertEquals("CPSC 110", course1.getCourseName());
        assertEquals("Computer Science", course1.getSubject());
        assertEquals(0, course1.getWeightingScheme().size());
        assertEquals(100, course1.getCourseGrade());
    }

    @Test
    public void testAddMarkEntryWithOneOverallWeighting() {
        course1.getWeightingScheme().add(allWeight);
        course1.addMarkEntry(all1);
        assertEquals(92, course1.getCourseGrade());

        course1.addMarkEntry(all2);
        assertEquals((all1.getMark() + all2.getMark())/2, course1.getCourseGrade());
    }

    @Test
    public void testAddMarkEntriesWithMultipleWeightingScheme() {
        course1.getWeightingScheme().add(mtWeight);
        course1.getWeightingScheme().add(assignmentWeight);
        course1.getWeightingScheme().add(finalWeight);
        course1.getWeightingScheme().add(participationWeight);
        course1.getWeightingScheme().add(quizWeight);
        assertEquals(5, course1.getWeightingScheme().size());

        course1.addMarkEntry(mt1);
        course1.addMarkEntry(mt2);
        assertEquals(Math.round((mt1.getMark() + mt2.getMark())/2), course1.getCourseGrade());

        course1.addMarkEntry(a1);
        course1.addMarkEntry(a2);
        course1.addMarkEntry(a3);
        assertEquals( Math.round((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark()) / 50 * 100) , course1.getCourseGrade());

        course1.addMarkEntry(participation);
        assertEquals((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark() + participationWeight.calculateWeightedMark()) / 53 * 100, course1.getCourseGrade());

        course1.addMarkEntry(q1);
        course1.addMarkEntry(q2);
        assertEquals((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark() + participationWeight.calculateWeightedMark() +
                quizWeight.calculateWeightedMark()) / 60 * 100, course1.getCourseGrade());

        course1.addMarkEntry(f1);
        assertEquals((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark() + participationWeight.calculateWeightedMark() +
                quizWeight.calculateWeightedMark() + finalWeight.calculateWeightedMark()), course1.getCourseGrade());
    }

    @Test
    public void testSettersGetters() {
        course1.setTeacher("A");
        assertEquals("A", course1.getTeacher());

        course1.setSubject("Biology");
        assertEquals("Biology", course1.getSubject());

        course1.setCourseName("ABC");
        assertEquals("ABC", course1.getCourseName());
    }

    @Test
    public void testCourseToJson() {
        JSONObject json = course1.toJson();
        assertEquals("{\"course name\":\"CPSC 110\",\"weightings\":[],\"subject\":" +
                "\"Computer Science\",\"course grade\":100}", json.toString());
    }

    @Test
    public void testCourseMultipleWeightingsJson() {
        course1.getWeightingScheme().add(mtWeight);
        course1.getWeightingScheme().add(finalWeight);
        JSONObject json = course1.toJson();
        assertEquals("{\"course name\":\"CPSC 110\",\"weightings\":[{\"weight\":30,\"marks\":[],\"category\":" +
                "\"midterm\"},{\"weight\":40,\"marks\":[],\"category\":\"final\"}],\"subject\":" +
                "\"Computer Science\",\"course grade\":100}", json.toString());
    }
    
}

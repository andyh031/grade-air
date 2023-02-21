package model;

import org.junit.jupiter.api.BeforeEach;
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
        assertEquals(93, course1.getCourseGrade());

        course1.addMarkEntry(all2);
        assertEquals(Math.round((all1.getMark() + all2.getMark())/2), course1.getCourseGrade());
    }

    @Test
    public void testAddMarkEntriesWithMultipleWeightingScheme() {
        course1.getWeightingScheme().add(mtWeight);
        course1.getWeightingScheme().add(assignmentWeight);
        course1.getWeightingScheme().add(finalWeight);
        course1.getWeightingScheme().add(participationWeight);
        course1.getWeightingScheme().add(quizWeight);

        course1.addMarkEntry(mt1);
        course1.addMarkEntry(mt2);
        assertEquals(Math.round((mt1.getMark() + mt2.getMark())/2), course1.getCourseGrade());

        course1.addMarkEntry(a1);
        course1.addMarkEntry(a2);
        course1.addMarkEntry(a3);
        assertEquals( Math.round((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark()) / 50 * 100) , course1.getCourseGrade());

        course1.addMarkEntry(participation);
        assertEquals( Math.round((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark() + participationWeight.calculateWeightedMark()) / 53 * 100), course1.getCourseGrade());

        course1.addMarkEntry(q1);
        course1.addMarkEntry(q2);
        assertEquals( Math.round((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark() + participationWeight.calculateWeightedMark() +
                quizWeight.calculateWeightedMark()) / 60 * 100), course1.getCourseGrade());

        course1.addMarkEntry(f1);
        assertEquals(Math.round((mtWeight.calculateWeightedMark() + assignmentWeight.calculateWeightedMark() + participationWeight.calculateWeightedMark() +
                quizWeight.calculateWeightedMark() + finalWeight.calculateWeightedMark())), course1.getCourseGrade());
    }

    @Test
    public void testSettersGetters() {
        course1.setTeacher("A");
        assertEquals("A", course1.getTeacher());

        course1.setGradeTaken("B");
        assertEquals("B", course1.getGradeTaken());
    }
    
}

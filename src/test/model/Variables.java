package model;

import org.junit.jupiter.api.BeforeEach;

public abstract class Variables {
    protected Student student;
    protected Course course1;
    protected Course course2;
    protected Weighting mtWeight;
    protected Weighting assignmentWeight;
    protected Weighting finalWeight;
    protected Weighting participationWeight;
    protected Weighting quizWeight;
    protected Weighting allWeight;
    protected MarkEntry mt1;
    protected MarkEntry mt2;
    protected MarkEntry a1;
    protected MarkEntry a2;
    protected MarkEntry a3;
    protected MarkEntry a4;
    protected MarkEntry a5;
    protected MarkEntry f1;
    protected MarkEntry participation;
    protected MarkEntry q1;
    protected MarkEntry q2;
    protected MarkEntry q3;
    protected MarkEntry all1;
    protected MarkEntry all2;

    @BeforeEach
    public void setup() {
        // Student
        student = new Student("Andy", "Hu");

        //Courses
        course1 = new Course("CPSC 110", "Computer Science");
        course2 = new Course("CPSC 210", "Computer Science");

        //Weightings
        mtWeight = new Weighting("midterm", 30);
        assignmentWeight = new Weighting("assignment", 20);
        finalWeight = new Weighting("final", 40);
        participationWeight = new Weighting("participation", 3);
        quizWeight = new Weighting("quiz", 7);
        allWeight = new Weighting("all", 100);

        //Mark Entries
        mt1 = new MarkEntry("mt1", 100, "midterm");
        mt2 = new MarkEntry("mt2", 80, "midterm");
        a1 = new MarkEntry("a1", 50, "assignment");
        a2 = new MarkEntry("a2", 80, "assignment");
        a3 = new MarkEntry("a3", 95, "assignment");
        f1 = new MarkEntry("f1", 70, "final");
        participation = new MarkEntry("participation", 70, "participation");
        q1 = new MarkEntry("q1", 97, "quiz");
        q2 = new MarkEntry("q2", 92, "quiz");
        all1 = new MarkEntry("all1", 93, "all");
        all2 = new MarkEntry("all2", 78, "all");
    }
}

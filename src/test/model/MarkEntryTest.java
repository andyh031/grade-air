package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarkEntryTest {
    protected MarkEntry mt1;
    protected MarkEntry mt2;
    protected MarkEntry a1;
    protected MarkEntry a2;
    protected MarkEntry a3;
    protected MarkEntry f1;
    protected MarkEntry participation;
    protected MarkEntry q1;
    protected MarkEntry q2;
    protected MarkEntry all1;
    protected MarkEntry all2;

    @BeforeEach
    public void setup1() {
        mt1 = new MarkEntry("mt1", 100, "midterm");
        mt2 = new MarkEntry("mt2", 80, "midterm");
        a1 = new MarkEntry("a1", 50, "assignment");
        a2 = new MarkEntry("a2", 80, "assignment");
        a3 = new MarkEntry("a3", 95, "assignment");
        f1 = new MarkEntry("f1", 70, "final");
        participation = new MarkEntry("participation", 90, "participation");
        q1 = new MarkEntry("q1", 97, "quiz");
        q2 = new MarkEntry("q2", 92, "quiz");
        all1 = new MarkEntry("all1", 92, "all");
        all2 = new MarkEntry("all2", 80, "all");
    }

    @Test
    public void testMarkEntryConstructor() {
        assertEquals("mt1", mt1.getName());
        assertEquals(100, mt1.getMark());
        assertEquals("midterm", mt1.getCategory());
    }

    @Test
    public void testMarkEntryToJson() {
        JSONObject json = mt1.toJson();
        String str = json.toString();
        assertEquals("{\"name\":\"mt1\",\"category\":\"midterm\",\"mark\":100}", str);
    }

}

package model;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeightingTest extends Variables {

    @Test
    public void testConstructor() {
        assertEquals("final", finalWeight.getCategory());
        assertEquals(40, finalWeight.getWeight());
        assertEquals(0, finalWeight.getMarksList().size());
    }

    @Test
    public void testAddMarkEntry() {
        mtWeight.addMarkEntry(mt1);
        assertEquals(1, mtWeight.getMarksList().size());
        assertEquals(mt1, mtWeight.getMarksList().get(0));

        mtWeight.addMarkEntry(mt2);
        assertEquals(2, mtWeight.getMarksList().size());
        assertEquals(mt1, mtWeight.getMarksList().get(0));
        assertEquals(mt2, mtWeight.getMarksList().get(1));
    }

    @Test
    public void testRetrieveMarksToPrint() {
        String str = mtWeight.retrieveMarksToPrint();
        assertEquals("", str);

        mtWeight.addMarkEntry(mt1);
        assertEquals("mt1: 100%\n", mtWeight.retrieveMarksToPrint());

        mtWeight.addMarkEntry(mt2);
        assertEquals("mt1: 100%\nmt2: 80%\n", mtWeight.retrieveMarksToPrint());
    }

    @Test
    public void testCalculateMtGrade() {
        assertEquals(0, mtWeight.calculateWeightedMark());
        mtWeight.addMarkEntry(mt1);
        double mark = mtWeight.calculateWeightedMark();
        assertEquals(mtWeight.getWeight() / mtWeight.getMarksList().size() * mt1.getMark() / 100, mark);

        mtWeight.addMarkEntry(mt2);
        mark = mtWeight.calculateWeightedMark();
        assertEquals( mtWeight.getWeight() / mtWeight.getMarksList().size() * (mt1.getMark() + mt2.getMark()) / 100, mark);
    }

    @Test
    public void testWeightingToJson() {
        JSONObject json = mtWeight.toJson();
        assertEquals("{\"weight\":30,\"marks\":[],\"category\":\"midterm\"}", json.toString());
    }

    @Test
    public void testWeightingMultipleMarksJson() {
        mtWeight.addMarkEntry(mt1);
        mtWeight.addMarkEntry(mt2);
        JSONObject json = mtWeight.toJson();
        assertEquals("{\"weight\":30,\"marks\":[{\"name\":\"mt1\",\"category\":" +
                "\"midterm\",\"mark\":100},{\"name\":\"mt2\",\"category\":\"midterm\",\"mark\":80}],\"category\":" +
                "\"midterm\"}", json.toString());
    }
}

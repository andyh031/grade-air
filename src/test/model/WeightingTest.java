package model;

import org.junit.jupiter.api.BeforeEach;
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
        assertEquals("mt1: 100\n", mtWeight.retrieveMarksToPrint());

        mtWeight.addMarkEntry(mt2);
        assertEquals("mt1: 100\nmt2: 80\n", mtWeight.retrieveMarksToPrint());
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
}

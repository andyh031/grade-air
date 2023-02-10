package model;

import java.util.ArrayList;
import java.util.List;

public class Weighting {
    private String category;
    private int weight;
    private List<MarkEntry> marksList;

    public Weighting(String category, int weight) {
        this.category = category;
        this.weight = weight;
        this.marksList = new ArrayList<>();
    }

    public void addMarkEntry(MarkEntry markEntry) {
        marksList.add(markEntry);
    }

    public String retrieveMarksToPrint() {
        String marks = "";
        for (MarkEntry markEntry : marksList) {
            marks += markEntry.getName() + ": " + markEntry.getMark() + "\n";
        }
        return marks;
    }

    public double calculateWeightedMark() {
        if (marksList.size() == 0) {
            return 0;
        } else {
            double mark = 0;
            for (MarkEntry markEntry : marksList) {
                double weightedMark = weight / marksList.size() * markEntry.getMark() / 100;
                mark += weightedMark;
            }
            return mark;
        }
    }

    // Getters
    public int getWeight() {
        return this.weight;
    }

    public List<MarkEntry> getMarksList() {
        return this.marksList;
    }

    public String getCategory() {
        return this.category;
    }
}

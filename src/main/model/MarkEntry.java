// Represents a mark within a student's weighting

package model;

public class MarkEntry {
    private String name;
    private String category;
    private double mark;

    //EFFECTS: creates a mark entry with the name of the mark, the mark received, and the category
    public MarkEntry(String name, double mark, String category) {
        this.name = name;
        this.mark = mark;
        this.category = category;
    }

    //Getters
    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public double getMark() {
        return this.mark;
    }
}

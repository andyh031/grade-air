// Represents a mark within a student's weighting

package model;

import org.json.JSONObject;
import persistence.Writable;

public class MarkEntry implements Writable {
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("category", category);
        json.put("mark", mark);
        return json;
    }
}

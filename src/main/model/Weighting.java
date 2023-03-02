// Represents a weighting in a course that also includes a list of mark entries within the specified weighting category

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class Weighting implements Writable {
    private String category;
    private double weight;
    private List<MarkEntry> marksList;

    //EFFECTS: creates a weighting with a category, numerical weight, and list of marks in that weighting
    public Weighting(String category, int weight) {
        this.category = category;
        this.weight = weight;
        this.marksList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a mark entry to student's list of marks
    public void addMarkEntry(MarkEntry markEntry) {
        marksList.add(markEntry);
    }

    //EFFECTS: retrieves all of the student's marks in a given weighting to print
    public String retrieveMarksToPrint() {
        String marks = "";
        for (MarkEntry markEntry : marksList) {
            marks += markEntry.getName() + ": " + (int)markEntry.getMark() + "%\n";
        }
        return marks;
    }

    //EFFECTS: returns a calculation of the weighted mark in a weighting
    public double calculateWeightedMark() {
        double mark = 0;
        for (MarkEntry markEntry : marksList) {
            double weightedMark = weight / marksList.size() * markEntry.getMark() / 100;
            mark += weightedMark;
        }
        return mark;
    }

    // Getters
    public double getWeight() {
        return this.weight;
    }

    public List<MarkEntry> getMarksList() {
        return this.marksList;
    }

    public String getCategory() {
        return this.category;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("category", category);
        json.put("weight", weight);
        json.put("marks", marksToJson());
        return json;
    }

    private JSONArray marksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (MarkEntry m : marksList) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}

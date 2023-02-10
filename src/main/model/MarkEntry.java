package model;

import java.util.Scanner;

import static java.util.Objects.isNull;

public class MarkEntry {
    private String name;
    private String category;
    private int mark;

    public MarkEntry(String name, int mark, String category) {
        this.name = name;
        this.mark = mark;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public int getMark() {
        return this.mark;
    }

    public void setScore(int mark) {
        this.mark = mark;
    }

}

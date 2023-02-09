package model;

import java.util.Scanner;

import static java.util.Objects.isNull;

public class MarkEntry {
    private String name;
    private String category;
    private int mark;
    private String deadline;
    private Scanner scanner;

    public MarkEntry() {
        scanner = new Scanner(System.in);

        System.out.print("Name: ");
        this.name = scanner.nextLine();

        System.out.print("Mark from 0-100 (press enter if no mark yet): ");
        this.mark = Integer.parseInt(scanner.nextLine());

        System.out.println("Type of mark (assignment, midterm, final, participation): ");
        this.category = scanner.nextLine();
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

    public String getDeadline() {
        return this.deadline;
    }

    public void setScore(int mark) {
        this.mark = mark;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}

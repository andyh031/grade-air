package model;

import java.util.Scanner;

public class SingleWeighting {
    private String category;
    private int weight;
    private Scanner scanner;

    public SingleWeighting() {
        scanner = new Scanner(System.in);

        System.out.print("Category: ");
        this.category = scanner.nextLine();

        System.out.print("Weight: ");
        this.weight = Integer.parseInt(scanner.nextLine());
    }


    // Getters
    public int getWeight() {
        return this.weight;
    }

    public String getCategory() {
        return this.category;
    }
}

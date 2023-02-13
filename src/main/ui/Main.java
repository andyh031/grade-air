// Runs the GradeAir application

package ui;

public class Main {
    public static void main(String[] args) {
        GradeAir gradeAir = new GradeAir();
        System.out.println("Welcome to GradeAir!\n");

        gradeAir.createAccount();
        gradeAir.end();
    }
}

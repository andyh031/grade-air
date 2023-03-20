package ui;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private Student student;

    public HomePanel(Student student) {
        this.setSize(new Dimension(400, 600));
        this.student = student;
        displayClasses();
    }

    public void displayClasses() {
        for (Course course : student.getCourses()) {
            ClassPanel classPanel = new ClassPanel(course);
            this.add(classPanel);
        }
    }
}

package ui;

import model.Course;

import javax.swing.*;
import java.awt.*;

public class ClassPanel extends JPanel {

    public ClassPanel(Course course) {
        this.setSize(new Dimension(1000, 1000));
        this.setBackground(Color.cyan);

        courseGradeLabel(course);
        courseNameLabel(course);

        this.setVisible(true);
    }

    private void courseGradeLabel(Course course) {
        JLabel grade = new JLabel(Double.toString(course.getCourseGrade()));
        this.add(grade);
        grade.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void courseNameLabel(Course course) {
        JLabel name = new JLabel(course.getCourseName());
        name.setHorizontalAlignment(SwingConstants.CENTER);
        name.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.add(name);
    }

    public static void main(String[] args) {
        Course course = new Course("BIO", "IDK", 95);
        new ClassPanel(course);

    }
}

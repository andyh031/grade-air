package ui;

import model.Course;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ClassPanel extends JPanel {

    public ClassPanel(Course course) {
        this.setBackground(new Color(0xF5FEFD));
        this.setPreferredSize(new Dimension(150, 150));

        JLabel grade = new JLabel(Double.toString(course.getCourseGrade()));
        this.add(grade);
        grade.setHorizontalAlignment(JLabel.CENTER);

        JLabel name = new JLabel(course.getCourseName());
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setVerticalAlignment(JLabel.BOTTOM);
        this.add(name);
        this.setVisible(true);
    }

}

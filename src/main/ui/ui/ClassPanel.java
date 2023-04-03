// Represents a panel for a class on the home screen, displaying the average for the class and its name

package ui.ui;

import model.Course;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import static ui.ui.HomePanel.CLASS_PANEL_COLOR;

public class ClassPanel extends JPanel {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private Course course;
    private JLabel grade;
    private JLabel name;

    //EFFECTS: constructs a class panel describing a course's name and grade in that class
    public ClassPanel(Course course) {
        this.course = course;
        this.setPreferredSize(new Dimension(150, 150));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.setBackground(CLASS_PANEL_COLOR);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        makeGrade(course);
        makeName(course);

        this.add(grade, BorderLayout.PAGE_END);
        this.add(name, BorderLayout.CENTER);
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a label for the class name
    private void makeName(Course course) {
        name = new JLabel(course.getCourseName());
        name.setFont(new Font(name.getName(), Font.PLAIN, 16));
        name.setHorizontalAlignment(JLabel.CENTER);
    }

    //MODIFIES: this
    //EFFECTS: creates a label for the class grade
    private void makeGrade(Course course) {
        grade = new JLabel(df.format(course.getCourseGrade()) + "%");
        grade.setFont(new Font(grade.getName(), Font.PLAIN, 16));
        grade.setHorizontalAlignment(JLabel.CENTER);
        grade.setBackground(new Color(0x33636b));
        grade.setForeground(Color.WHITE);
        grade.setOpaque(true);
    }

    // Getter
    public Course getCourse() {
        return this.course;
    }
}

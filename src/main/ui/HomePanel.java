package ui;

import model.Course;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel implements ActionListener {
    private Student student;

    public HomePanel(Student student) {
        JLabel dashboard = new JLabel("Dashboard");
        JLabel gpa = new JLabel("GPA: " + student.calculateGPA());
        JButton addClass = new JButton("Add");
        addClass.addActionListener(this);
        addClass.setFont(new Font(addClass.getName(), Font.PLAIN, 20));
        dashboard.setFont(new Font(dashboard.getName(), Font.PLAIN, 20));
        gpa.setFont(new Font(gpa.getName(), Font.PLAIN, 20));

        this.setSize(new Dimension(400, 600));
        this.setBackground(Color.white);
        this.student = student;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        this.add(dashboard, c);
        dashboard.setHorizontalAlignment(JLabel.LEFT);
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        this.add(gpa, c);
        this.setVisible(true);
        displayClasses(c);
        this.add(addClass, c);
    }

    public void displayClasses(GridBagConstraints c) {
        for (int i = 0; i < student.getCourses().size(); i++) {
            ClassPanel classPanel = new ClassPanel(student.getCourses().get(i));
            if (i % 2 == 0) {
                c.gridx = 0;
            } else {
                c.gridx = 1;
            }
            this.add(classPanel, c);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

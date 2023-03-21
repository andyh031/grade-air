package ui.ui;

import model.Course;
import model.Student;
import persistence.JsonWriter;
import ui.ui.forms.WeightingForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class HomePanel extends JPanel implements ActionListener {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String JSON_STORE = "./data/student.json";
    private Student student;
    private JButton saveButton;
    private JButton addClass;
    private JLabel dashboard;
    private JLabel gpa;
    private JsonWriter jsonWriter;

    public HomePanel(Student student) {
        jsonWriter = new JsonWriter(JSON_STORE);
        this.setSize(new Dimension(400, 600));
        this.setBackground(Color.white);
        this.student = student;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        makeDashboardLabel();
        makeGpaLabel(student);
        makeClassButton();
        makeSaveButton();

        this.add(dashboard, c);
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        this.add(gpa, c);
        displayClasses(c);
        c.gridx = 0;
        this.add(addClass, c);
        this.add(saveButton, c);
        this.setVisible(true);
    }

    private void makeGpaLabel(Student student) {
        gpa = new JLabel("GPA: " + df.format(student.calculateGPA()) + "%");
        gpa.setFont(new Font(gpa.getName(), Font.PLAIN, 20));
    }

    private void makeDashboardLabel() {
        dashboard = new JLabel("Dashboard");
        dashboard.setFont(new Font(dashboard.getName(), Font.PLAIN, 20));
        dashboard.setHorizontalAlignment(JLabel.LEFT);
    }

    private void makeSaveButton() {
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
        saveButton.setFont(new Font(saveButton.getName(), Font.PLAIN, 20));
    }

    private void makeClassButton() {
        addClass = new JButton("Add");
        addClass.addActionListener(this);
        addClass.setFont(new Font(addClass.getName(), Font.PLAIN, 20));
    }

    public void displayClasses(GridBagConstraints c) {
        for (int i = 0; i < student.getCourses().size(); i++) {
            Course course = student.getCourses().get(i);
            ClassPanel classPanel = new ClassPanel(course);
            if (i % 2 == 0) {
                c.gridx = 0;
            } else {
                c.gridx = 1;
            }
            this.add(classPanel, c);
            classPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    new ClassInfoFrame(course);
                }
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addClass) {
            // TODO
            new WeightingForm(student);
        } else if (e.getSource() == saveButton) {
            try {
                jsonWriter.open();
                jsonWriter.write(student);
                jsonWriter.close();
            } catch (FileNotFoundException err) {

            }
        }

    }
}

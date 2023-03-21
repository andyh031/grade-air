package ui.ui.forms;

import model.Course;
import model.Student;
import model.Weighting;
import ui.ui.GradeAirFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class WeightingForm extends JFrame implements ActionListener {
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_WIDTH = 300;
    private static final int ONE_LINE_HEIGHT = 20;
    private static final Dimension TEXT_DIMENSION = new Dimension(TEXT_WIDTH, ONE_LINE_HEIGHT);
    private static final Dimension LABEL_DIMENSION = new Dimension(LABEL_WIDTH, ONE_LINE_HEIGHT);
    private List<Weighting> weightingList = new ArrayList<>();
    private JTextField weightIntText;
    private JTextField weightText;
    private JButton addButton;
    private JButton submitButton;
    private Student student;
    private JLabel name;
    private JTextField nameText;
    private JLabel subject;
    private JTextField subjectText;
    private int total = 0;
    private JLabel totalLabel;


    public WeightingForm(Student student) {
        super("Add Class");
        this.student = student;
        this.setSize(450, 600);
        this.setLayout(new FlowLayout());

        makeNameField();
        makeSubjectField();
        makeWeightingsLabel();
        makeAddButton();
        makeSubmitButton();
        makeTotalLabel();
        weightSection();

        this.add(addButton);
        this.add(submitButton);
        this.add(totalLabel);
        this.setVisible(true);
    }

    private void makeTotalLabel() {
        totalLabel = new JLabel();
        totalLabel.setText("Total Weight: " + total);
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        totalLabel.setPreferredSize(new Dimension(300, 20));
    }

    private void makeSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(80, 20));
        submitButton.addActionListener(this);
    }

    private void makeAddButton() {
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 20));
        addButton.addActionListener(this);
    }

    private void makeWeightingsLabel() {
        JLabel weightLabel = new JLabel("Weightings");
        weightLabel.setPreferredSize(new Dimension(400, 20));
        this.add(weightLabel);
    }

    private void makeSubjectField() {
        subject = new JLabel("Subject: ");
        subjectText = new JTextField();
        subject.setPreferredSize(LABEL_DIMENSION);
        subjectText.setPreferredSize(TEXT_DIMENSION);
        this.add(subject);
        this.add(subjectText);
    }

    private void makeNameField() {
        name = new JLabel("Class Name: ");
        nameText = new JTextField();
        name.setPreferredSize(LABEL_DIMENSION);
        nameText.setPreferredSize(TEXT_DIMENSION);
        this.add(name);
        this.add(nameText);
    }

    public void weightSection() {
        JLabel nameLabel = new JLabel("Name of Weight");
        nameLabel.setPreferredSize(LABEL_DIMENSION);
        weightText = new JTextField();
        weightText.setPreferredSize(TEXT_DIMENSION);

        JLabel weightIntLabel = new JLabel("Weight Amount");
        weightIntLabel.setPreferredSize(LABEL_DIMENSION);
        weightIntText = new JTextField();
        weightIntText.setPreferredSize(TEXT_DIMENSION);

        this.add(nameLabel);
        this.add(weightText);
        this.add(weightIntLabel);
        this.add(weightIntText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            weightingList.add(new Weighting(weightText.getText(), Integer.parseInt(weightIntText.getText())));
            total += Integer.parseInt(weightIntText.getText());
            totalLabel.setText("Total Weight: " + total);

        } else if (e.getSource() == submitButton) {
            if (total == 100) {
                Course course = new Course(nameText.getText(), subjectText.getText());
                course.setWeightingScheme(weightingList);
                student.addCourse(course);
                this.dispose();
                new GradeAirFrame(student);
            }
        }
    }
}

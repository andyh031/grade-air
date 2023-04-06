// Creates a form for the make a new class: they must provide the class name, subject, and give
// the weighting scheme for the class

package ui.gui.forms;

import model.Course;
import model.Student;
import model.Weighting;
import ui.gui.GradeAirFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class WeightingForm extends Form {
    private List<Weighting> weightingList = new ArrayList<>();
    private JTextField weightIntText;
    private JTextField weightText;
    private JButton addButton;
    private JLabel name;
    private JTextField nameText;
    private JLabel subject;
    private JTextField subjectText;
    private int total = 0;
    private JLabel totalLabel;

    // EFFECTS: Creates a form for user to add a new course
    public WeightingForm(Student student) {
        super("Add Class");
        this.student = student;


        makeHeading("Add Class");
        makeNameField();
        makeSubjectField();
        makeWeightingsLabel();
        weightSection();
        makeAddButton();
        makeSubmitButton();

        makeTotalLabel();
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a label which updates to show how much percentage of weighting has been allocated so far
    private void makeTotalLabel() {
        totalLabel = new JLabel();
        totalLabel.setText("Total Weight: " + total);
        totalLabel.setHorizontalAlignment(JLabel.CENTER);
        totalLabel.setPreferredSize(new Dimension(300, 20));
        this.add(totalLabel);
    }

    //MODIFIES: this
    //EFFECTS: creates a button for the user to click to add a weighting to the class
    private void makeAddButton() {
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 20));
        addButton.addActionListener(this);
        this.add(addButton);
    }

    //EFFECTS: creates a label for "weightings"
    private void makeWeightingsLabel() {
        JLabel weightLabel = new JLabel("Weightings");
        weightLabel.setPreferredSize(new Dimension(400, 20));
        this.add(weightLabel);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for user to type in the subject of the class
    private void makeSubjectField() {
        subject = new JLabel("Subject: ");
        subjectText = new JTextField();
        subject.setPreferredSize(LABEL_DIMENSION);
        subjectText.setPreferredSize(TEXT_DIMENSION);
        this.add(subject);
        this.add(subjectText);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to type in the name of the class
    private void makeNameField() {
        name = new JLabel("Class Name: ");
        nameText = new JTextField();
        name.setPreferredSize(LABEL_DIMENSION);
        nameText.setPreferredSize(TEXT_DIMENSION);
        this.add(name);
        this.add(nameText);
    }

    //MODIFIES: this
    //EFFECTS: creates a section for users to initialize the weighting scheme of the class
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

    //MODIFIES: student
    //EFFECTS: when user presses add button, add the weighting to the current course weighting
    //          when user presses submit button, add the course to the stduent's list of courses
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

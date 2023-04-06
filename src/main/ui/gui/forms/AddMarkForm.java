// A popup window for the user to add a mark to a class

package ui.gui.forms;

import model.Course;
import model.MarkEntry;
import model.Student;
import model.Weighting;
import ui.gui.ClassInfoFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

public class AddMarkForm extends Form {
    private JTextField markNameText;
    private JTextField markGradeText;
    private JLabel markNameLabel;
    private JLabel markGradeLabel;
    private JLabel markCategoryLabel;
    private JComboBox markCategoryText;
    private Course course;
    private List<String> weightingNameList = new ArrayList<>();
    private String[] weightings;

    // EFFECTS: Creates a form for user to input a mark name, grade, and category of its weighting
    public AddMarkForm(Student student, Course course) {
        super("Add Mark");
        this.student = student;
        this.course = course;
        this.addWindowListener(new CloseMarkForm());
        weightingsArray(course);

        makeHeading("Add a Mark");
        makeMarkNameField();
        makeMarkGradeField();
        makeMarkCategoryField();
        makeSubmitButton();
        this.add(submitButton);
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: makes a field for the user to select the category(in a weighting) of the mark
    private void makeMarkCategoryField() {
        markCategoryLabel = new JLabel("Category");
        markCategoryLabel.setPreferredSize(LABEL_DIMENSION);
        markCategoryText = new JComboBox(weightings);
        markCategoryText.setPreferredSize(TEXT_DIMENSION);
        this.add(markCategoryLabel);
        this.add(markCategoryText);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to enter their grade achieved for the rsepective mark
    private void makeMarkGradeField() {
        markGradeLabel = new JLabel("Grade");
        markGradeLabel.setPreferredSize(LABEL_DIMENSION);
        markGradeText = new JTextField();
        markGradeText.setPreferredSize(TEXT_DIMENSION);
        this.add(markGradeLabel);
        this.add(markGradeText);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to enter the name of the mark
    private void makeMarkNameField() {
        markNameLabel = new JLabel("Mark Name");
        markNameLabel.setPreferredSize(LABEL_DIMENSION);
        markNameText = new JTextField();
        markNameText.setPreferredSize(TEXT_DIMENSION);
        this.add(markNameLabel);
        this.add(markNameText);
    }

    //MODIFIES: this
    //EFFECTS: adds weighting names to an array for use of the JComboBox selector
    private void weightingsArray(Course course) {
        for (Weighting weighting : course.getWeightingScheme()) {
            weightingNameList.add(weighting.getCategory());
        }
        weightings = weightingNameList.toArray(new String[0]);
    }

    //MODIFIES: student, course
    //EFFECTS: when user clicks submit button, add the mark information to the course
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = markNameText.getText();
        Double grade = Double.parseDouble(markGradeText.getText());
        String category = (String)markCategoryText.getSelectedItem();
        MarkEntry mark = new MarkEntry(name, grade, category);

        course.addMarkEntry(mark);
        this.dispose();
    }

    // Operation upon closing window
    private class CloseMarkForm implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {

        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        // EFFECTS: creates a class information frame upon closing the add mark form
        @Override
        public void windowDeactivated(WindowEvent e) {
            new ClassInfoFrame(student, course);
        }
    }
}

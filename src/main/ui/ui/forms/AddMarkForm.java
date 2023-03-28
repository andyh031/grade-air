// A popup window for the user to add a mark to a class

package ui.ui.forms;

import model.Course;
import model.MarkEntry;
import model.Student;
import model.Weighting;
import ui.ui.ClassInfoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddMarkForm extends JFrame implements ActionListener, Form {
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_WIDTH = 300;
    private static final int ONE_LINE_HEIGHT = 20;
    private static final Dimension TEXT_DIMENSION = new Dimension(TEXT_WIDTH, ONE_LINE_HEIGHT);
    private static final Dimension LABEL_DIMENSION = new Dimension(LABEL_WIDTH, ONE_LINE_HEIGHT);
    private JTextField markNameText;
    private JTextField markGradeText;
    private JLabel markNameLabel;
    private JLabel markGradeLabel;
    private JLabel markCategoryLabel;
    private JButton submitButton;
    private JComboBox markCategoryText;
    private Course course;
    private List<String> weightingNameList = new ArrayList<>();
    private String[] weightings;
    private Student student;

    // EFFECTS: Creates a form for user to input a mark name, grade, and category of its weighting
    public AddMarkForm(Student student, Course course) {
        super("Add Mark");
        this.student = student;
        this.course = course;
        weightingsArray(course);
        this.setSize(450, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());

        makeMarkNameField();
        makeMarkGradeField();
        makeMarkCategoryField();
        makeSubmitButton();
        this.setVisible(true);
    }

    // EFFECTS: creates a button to click when user is done entering mark
    @Override
    public void makeSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(this);
        this.add(submitButton);
    }

    //EFFECTS: makes a field for the user to select the category(in a weighting) of the mark
    private void makeMarkCategoryField() {
        markCategoryLabel = new JLabel("Category");
        markCategoryLabel.setPreferredSize(LABEL_DIMENSION);
        markCategoryText = new JComboBox(weightings);
        markCategoryText.setPreferredSize(TEXT_DIMENSION);
        this.add(markCategoryLabel);
        this.add(markCategoryText);
    }

    //EFFECTS: creates a field for the user to enter their grade achieved for the rsepective mark
    private void makeMarkGradeField() {
        markGradeLabel = new JLabel("Grade");
        markGradeLabel.setPreferredSize(LABEL_DIMENSION);
        markGradeText = new JTextField();
        markGradeText.setPreferredSize(TEXT_DIMENSION);
        this.add(markGradeLabel);
        this.add(markGradeText);
    }

    //EFFECTS: creates a field for the user to enter the name of the mark
    private void makeMarkNameField() {
        markNameLabel = new JLabel("Mark Name");
        markNameLabel.setPreferredSize(LABEL_DIMENSION);
        markNameText = new JTextField();
        markNameText.setPreferredSize(TEXT_DIMENSION);
        this.add(markNameLabel);
        this.add(markNameText);
    }

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
        new ClassInfoFrame(student, course);
    }
}

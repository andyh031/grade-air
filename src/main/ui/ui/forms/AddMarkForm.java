package ui.ui.forms;

import model.Course;
import model.MarkEntry;
import model.Weighting;
import ui.ui.ClassInfoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddMarkForm extends JFrame implements ActionListener {
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

    public AddMarkForm(Course course) {
        super("Add Mark");
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

    private void makeSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(this);
        this.add(submitButton);
    }

    private void makeMarkCategoryField() {
        markCategoryLabel = new JLabel("Category");
        markCategoryLabel.setPreferredSize(LABEL_DIMENSION);
        markCategoryText = new JComboBox(weightings);
        markCategoryText.setPreferredSize(TEXT_DIMENSION);
        this.add(markCategoryLabel);
        this.add(markCategoryText);
    }

    private void makeMarkGradeField() {
        markGradeLabel = new JLabel("Grade");
        markGradeLabel.setPreferredSize(LABEL_DIMENSION);
        markGradeText = new JTextField();
        markGradeText.setPreferredSize(TEXT_DIMENSION);
        this.add(markGradeLabel);
        this.add(markGradeText);
    }

    private void makeMarkNameField() {
        markNameLabel = new JLabel("Mark Name");
        markNameLabel.setPreferredSize(LABEL_DIMENSION);
        markNameText = new JTextField();
        markNameText.setPreferredSize(TEXT_DIMENSION);
        this.add(markNameLabel);
        this.add(markNameText);
    }

    private void weightingsArray(Course course) {
        for (Weighting weighting : course.getWeightingScheme()) {
            weightingNameList.add(weighting.getCategory());
        }
        weightings = weightingNameList.toArray(new String[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = markNameText.getText();
        Double grade = Double.parseDouble(markGradeText.getText());
        String category = (String)markCategoryText.getSelectedItem();
        MarkEntry mark = new MarkEntry(name, grade, category);

        course.addMarkEntry(mark);
        this.dispose();
        new ClassInfoFrame(course);
    }
}

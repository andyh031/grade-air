// A popup window for the user to edit the course information of a class, specifically,
// course name, subject, and teacher name

package ui.ui.forms;

import model.Course;
import model.Student;
import ui.ui.ClassInfoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClassInfoForm extends JFrame implements ActionListener, Form {
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_WIDTH = 300;
    private static final int ONE_LINE_HEIGHT = 20;
    private static final Dimension TEXT_DIMENSION = new Dimension(TEXT_WIDTH, ONE_LINE_HEIGHT);
    private static final Dimension LABEL_DIMENSION = new Dimension(LABEL_WIDTH, ONE_LINE_HEIGHT);
    private JLabel courseNameLabel;
    private JTextField courseNameText;
    private JLabel subjectLabel;
    private JTextField subjectText;
    private JLabel teacherLabel;
    private JTextField teacherText;
    private JButton submitButton;
    private Course course;
    private Student student;

    // EFFECTS: Creates a form for user to edit class information
    public EditClassInfoForm(Student student, Course course) {
        super("Class Information");
        this.student = student;
        this.course = course;
        this.setSize(450, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());

        makeCourseNameField(course);
        makeSubjectField(course);
        makeTeacherField(course);
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

    //EFFECTS: creates a field for the user to change the teacher name
    private void makeTeacherField(Course course) {
        teacherLabel = new JLabel("Teacher");
        teacherLabel.setPreferredSize(LABEL_DIMENSION);
        teacherText = new JTextField(course.getTeacher());
        teacherText.setPreferredSize(TEXT_DIMENSION);
        this.add(teacherLabel);
        this.add(teacherText);
    }

    //EFFECTS: creates a field for the user to change the subject of the class
    private void makeSubjectField(Course course) {
        subjectLabel = new JLabel("Subject");
        subjectLabel.setPreferredSize(LABEL_DIMENSION);
        subjectText = new JTextField(course.getSubject());
        subjectText.setPreferredSize(TEXT_DIMENSION);
        this.add(subjectLabel);
        this.add(subjectText);
    }

    //EFFECTS: creates a field for the user to change the name of the course
    private void makeCourseNameField(Course course) {
        courseNameLabel = new JLabel("Course Name");
        courseNameLabel.setPreferredSize(LABEL_DIMENSION);
        courseNameText = new JTextField(course.getCourseName());
        courseNameText.setPreferredSize(TEXT_DIMENSION);
        this.add(courseNameLabel);
        this.add(courseNameText);
    }

    //MODIFIES: student, course
    //EFFECTS: when user clicks submit button, change the course information to that entered in the form
    @Override
    public void actionPerformed(ActionEvent e) {
        course.setCourseName(courseNameText.getText());
        course.setSubject(subjectText.getText());
        course.setTeacher(teacherText.getText());
        this.dispose();
        new ClassInfoFrame(student, course);
    }
}
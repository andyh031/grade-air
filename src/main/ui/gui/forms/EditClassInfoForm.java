// A popup window for the user to edit the course information of a class, specifically,
// course name, subject, and teacher name

package ui.gui.forms;

import model.Course;
import model.Student;
import ui.gui.ClassInfoFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class EditClassInfoForm extends Form {
    private JLabel courseNameLabel;
    private JTextField courseNameText;
    private JLabel subjectLabel;
    private JTextField subjectText;
    private JLabel teacherLabel;
    private JTextField teacherText;
    private Course course;

    // EFFECTS: Creates a form for user to edit class information
    public EditClassInfoForm(Student student, Course course) {
        super("Class Information");
        this.student = student;
        this.course = course;
        this.addWindowListener(new CloseClassForm());

        makeHeading("Course Information");
        makeCourseNameField(course);
        makeSubjectField(course);
        makeTeacherField(course);
        makeSubmitButton();

        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to change the teacher name
    private void makeTeacherField(Course course) {
        teacherLabel = new JLabel("Teacher");
        teacherLabel.setPreferredSize(LABEL_DIMENSION);
        teacherText = new JTextField(course.getTeacher());
        teacherText.setPreferredSize(TEXT_DIMENSION);
        this.add(teacherLabel);
        this.add(teacherText);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to change the subject of the class
    private void makeSubjectField(Course course) {
        subjectLabel = new JLabel("Subject");
        subjectLabel.setPreferredSize(LABEL_DIMENSION);
        subjectText = new JTextField(course.getSubject());
        subjectText.setPreferredSize(TEXT_DIMENSION);
        this.add(subjectLabel);
        this.add(subjectText);
    }

    //MODIFIES: this
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
    }

    //Window closing operations
    private class CloseClassForm implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        //EFFECTS: generates a class information frame upon closing the edit class form
        @Override
        public void windowClosed(WindowEvent e) {
            new ClassInfoFrame(student, course);
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

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    }
}
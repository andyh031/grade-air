// Displays a specific class' information to the user

package ui.ui;

import model.Course;
import model.MarkEntry;
import model.Student;
import model.Weighting;
import ui.ui.forms.AddMarkForm;
import ui.ui.forms.EditClassInfoForm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

public class ClassInfoFrame extends JFrame implements ActionListener {
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final int WINDOW_HEIGHT = 600;
    private static final int ONE_LINE_HEIGHT = 20;
    private static final int WINDOW_WIDTH = 400;
    private static final Dimension ONE_LINE_DIMENSION = new Dimension(WINDOW_WIDTH, ONE_LINE_HEIGHT);
    private Student student;
    private Course course;
    private JButton addMark;
    private JButton edit;
    private JButton backButton;
    private JLabel title;
    private JPanel info;
    private JPanel buttons;
    private JPanel grades;
    private Border border;
    private Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black);

    //EFFECTS: creates a frame for the class, showing its name, information fields, and grades. User
    //          is also given the option to add marks to the class and edit class information
    public ClassInfoFrame(Student student, Course course) {
        super(course.getCourseName());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());
        this.course = course;
        this.student = student;
        border = BorderFactory.createLineBorder(Color.black, 2);
        this.setSize(450, ClassInfoFrame.WINDOW_HEIGHT);
        this.setLayout(new FlowLayout());

        makeTitle(course);
        makeInformation(course);
        makeButtons();
        makeGrades(course);
        makeBackButton();

        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates the back button to go 'back'
    public void makeBackButton() {
        backButton = new JButton("Back");
        backButton.setVerticalAlignment(JButton.BOTTOM);
        backButton.addActionListener(this);
        this.add(backButton);
    }

    //MODIFIES: this
    //EFFECTS: creates a panel to display all the course marks
    private void makeGrades(Course course) {
        grades = new JPanel();
        grades.setPreferredSize(new Dimension(WINDOW_WIDTH, 350));
        grades.setLayout(new FlowLayout());
        List<Weighting> weightingScheme = course.getWeightingScheme();

        for (int i = 0; i < weightingScheme.size(); i++) {
            JLabel weightName = new JLabel(weightingScheme.get(i).getCategory());
            weightName.setPreferredSize(ONE_LINE_DIMENSION);
            grades.add(weightName);

            for (MarkEntry markEntry : weightingScheme.get(i).getMarksList()) {
                JLabel mark = new JLabel(markEntry.getName() + ": " + markEntry.getMark() + "%");
                mark.setPreferredSize(ONE_LINE_DIMENSION);

                grades.add(mark);
            }
            weightName.setBorder(bottomBorder);

        }
        this.add(grades);
    }

    //MODIFIES: this
    //EFFECTS: creates an add and edit button for user to add a mark, or edit class information
    private void makeButtons() {
        buttons = new JPanel();
        buttons.setSize(WINDOW_WIDTH, 50);
        buttons.setLayout(new GridLayout(0, 2));
        addMark = new JButton("Add Mark");
        edit = new JButton("Edit");
        buttons.add(addMark);
        buttons.add(edit);
        buttons.setBorder(border);
        addMark.addActionListener(this);
        edit.addActionListener(this);
        this.add(buttons);
    }

    //MODIFIES: this
    //EFFECTS: displays gpa, subject, and teacher of the class
    private void makeInformation(Course course) {
        info = new JPanel();
        info.setPreferredSize(new Dimension(WINDOW_WIDTH, 75));
        JLabel gpa = new JLabel("GPA: " + df.format(course.getCourseGrade()) + "%");
        gpa.setPreferredSize(ONE_LINE_DIMENSION);
        JLabel subject = new JLabel("Subject: " + course.getSubject());
        subject.setPreferredSize(ONE_LINE_DIMENSION);

        JLabel teacher = new JLabel("Teacher: " + course.getTeacher());
        teacher.setPreferredSize(ONE_LINE_DIMENSION);
        if (course.getTeacher() == null || course.getTeacher().length() == 0) {
            teacher.setVisible(false);
        } else {
            teacher.setVisible(true);
        }

        info.add(gpa);
        info.add(subject);
        info.add(teacher);
        info.setBorder(bottomBorder);
        this.add(info);
    }

    //MODIFIES: this
    //EFFECTS: creates the heading, showing the name of the class at the top of the frame
    private void makeTitle(Course course) {
        title = new JLabel(course.getCourseName());
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        title.setFont(new Font(title.getName(), Font.PLAIN, 30));
        title.setBorder(bottomBorder);
        this.add(title);
    }

    //EFFECTS: if user hits edit, bring up the edit class form for user to edit class informatoin
    //         if user hits add mark, bring up the add mark form so user can add a mark
    //         if user hits back button, go back to the home page
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == edit) {
            this.dispose();
            new EditClassInfoForm(student, course);
        } else if (e.getSource() == addMark) {
            this.dispose();
            new AddMarkForm(student, course);
        } else if (e.getSource() == backButton) {
            new GradeAirFrame(student);
        }
    }


}

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

import static ui.ui.HomePanel.BACKGROUND_COLOR;

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
    private JPanel title;
    private JPanel info;
    private JPanel buttons;
    private JPanel grades;
    private Border border;

    //EFFECTS: creates a frame for the class, showing its name, information fields, and grades. User
    //          is also given the option to add marks to the class and edit class information
    public ClassInfoFrame(Student student, Course course) {
        super(course.getCourseName());
        this.getContentPane().setBackground(BACKGROUND_COLOR);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

        this.add(title);
        this.add(info);
        this.add(buttons);
        this.add(grades);
        this.add(backButton);

        this.setVisible(true);
    }

    //EFFECTS: creates a panel to display all the course marks
    private void makeGrades(Course course) {
        grades = new JPanel();
        grades.setPreferredSize(new Dimension(WINDOW_WIDTH, 300));
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
        }
    }

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
    }

    private void makeBackButton() {
        backButton = new JButton("Back");
        backButton.addActionListener(this);
    }

    //EFFECTS: displays gpa, subject, and teacher of the class
    private void makeInformation(Course course) {
        info = new JPanel();
        info.setPreferredSize(new Dimension(WINDOW_WIDTH, 75));
        JLabel gpa = new JLabel("GPA: " + df.format(course.getCourseGrade()));
        gpa.setPreferredSize(ONE_LINE_DIMENSION);
        gpa.setHorizontalAlignment(JLabel.CENTER);
        JLabel subject = new JLabel("SUBJECT: " + course.getSubject());
        subject.setPreferredSize(ONE_LINE_DIMENSION);
        subject.setHorizontalAlignment(JLabel.CENTER);
        JLabel teacher = new JLabel("TEACHER: " + course.getTeacher());
        teacher.setPreferredSize(ONE_LINE_DIMENSION);
        teacher.setHorizontalAlignment(JLabel.CENTER);
        info.add(gpa);
        info.add(subject);
        info.add(teacher);
        info.setBorder(border);
    }

    //EFFECTS: creates the heading, showing the name of the class at the top of the frame
    private void makeTitle(Course course) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        title = new JPanel();
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        JLabel className = new JLabel(course.getCourseName());
        title.add(className);
        className.setFont(new Font(className.getName(), Font.PLAIN, 30));
        title.setBorder(border);
    }

    //EFFECTS: closes the window and either bring up the 'add mark form' or the 'edit info form'
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

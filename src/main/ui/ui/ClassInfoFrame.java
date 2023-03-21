package ui.ui;

import model.Course;
import model.MarkEntry;
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
    private Course course;
    private JButton addMark;
    private JButton edit;
    private JPanel title;
    private JPanel info;
    private JPanel buttons;
    private JPanel grades;
    private Border border;

    public ClassInfoFrame(Course course) {
        super(course.getCourseName());
        this.course = course;
        border = BorderFactory.createLineBorder(Color.black, 2);
        this.setSize(450, ClassInfoFrame.WINDOW_HEIGHT);
        this.setLayout(new FlowLayout());

        makeTitle(course);
        makeInformation(course);
        makeButtons();
        makeGrades(course);

        this.add(title);
        this.add(info);
        this.add(buttons);
        this.add(grades);

        this.setVisible(true);
    }

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

    private void makeTitle(Course course) {
        title = new JPanel();
        title.setPreferredSize(new Dimension(WINDOW_WIDTH, 50));
        JLabel className = new JLabel(course.getCourseName());
        title.add(className);
        className.setFont(new Font(className.getName(), Font.PLAIN, 30));
        title.setBorder(border);
    }

    public static void main(String[] args) {
        new ClassInfoFrame(new Course("BIO", "BIO", "MR", 95));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == edit) {
            this.dispose();
            new EditClassInfoForm(course);
        } else if (e.getSource() == addMark) {
            this.dispose();
            new AddMarkForm(course);
        }
    }
}

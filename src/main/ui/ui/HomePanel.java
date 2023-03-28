package ui.ui;

import model.Course;
import model.Student;
import persistence.JsonWriter;
import ui.ui.forms.WeightingForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class HomePanel extends JPanel implements ActionListener {
    public static final Color CLASS_PANEL_COLOR = new Color(0xE5E5E5);
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String JSON_STORE = "./data/student.json";
    private Student student;
    private JButton saveButton;
    private JButton addClass;
    private JToggleButton removeButton;
    private JLabel dashboard;
    private JLabel gpa;
    private JsonWriter jsonWriter;
    private GradeAirFrame gaf;

    public HomePanel(Student student, GradeAirFrame gaf) {
        this.gaf = gaf;
        jsonWriter = new JsonWriter(JSON_STORE);
        this.setSize(new Dimension(400, 600));
        this.setBackground(Color.white);
        this.student = student;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        makeDashboardLabel();
        makeGpaLabel(student);
        makeAddClassButton();
        makeSaveButton();
        makeRemoveButton();

        this.add(dashboard, c);
        this.add(gpa, c);
        displayClasses(c);
        c.gridx = 0;
        this.add(addClass, c);
        this.add(saveButton, c);
        this.add(removeButton, c);
        this.setVisible(true);
    }

    private void makeGpaLabel(Student student) {
        gpa = new JLabel("GPA: " + df.format(student.calculateGPA()) + "%");
        gpa.setFont(new Font(gpa.getName(), Font.PLAIN, 20));
    }

    private void makeDashboardLabel() {
        dashboard = new JLabel("Dashboard");
        dashboard.setFont(new Font(dashboard.getName(), Font.PLAIN, 20));
        dashboard.setHorizontalAlignment(JLabel.LEFT);
    }

    private void makeRemoveButton() {
        removeButton = new JToggleButton("Remove Class");
        removeButton.addActionListener(this);
    }

    private void makeSaveButton() {
        saveButton = new JButton("Save");
        saveButton.addActionListener(this);
    }

    private void makeAddClassButton() {
        addClass = new JButton("Add");
        addClass.addActionListener(this);
    }

    public void displayClasses(GridBagConstraints c) {
        for (int i = 0; i < student.getCourses().size(); i++) {
            Course course = student.getCourses().get(i);
            ClassPanel classPanel = new ClassPanel(course);
            if (i % 2 == 0) {
                c.gridx = 0;
            } else {
                c.gridx = 1;
            }
            this.add(classPanel, c);
            classPanel.addMouseListener(new ClickClassListener());
        }
    }

    public class ClickClassListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof ClassPanel) {
                ClassPanel panel = (ClassPanel) e.getSource();
                if (!removeButton.isSelected()) {
                    new ClassInfoFrame(student, panel.getCourse());
                    gaf.dispose();
                } else {
                    student.getCourses().remove(panel.getCourse());
                    gaf.dispose();
                    new GradeAirFrame(student);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() instanceof ClassPanel) {
                ClassPanel panel = (ClassPanel)e.getSource();
                panel.setBackground(new Color(0xCDCDCD));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() instanceof ClassPanel) {
                ClassPanel panel = (ClassPanel)e.getSource();
                panel.setBackground(CLASS_PANEL_COLOR);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addClass) {
            new WeightingForm(student);
        } else if (e.getSource() == saveButton) {
            try {
                jsonWriter.open();
                jsonWriter.write(student);
                jsonWriter.close();
            } catch (FileNotFoundException err) {

            }
        }
    }
}

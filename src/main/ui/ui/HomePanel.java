// Represents the panel of the home screen with all of the classes, overall student GPA

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
    private GridBagConstraints gbc;
    private JLabel sorry;

    //EFFECTS: generates the home panel with dashboard, gpa, classes, and buttons to edit information/classes
    public HomePanel(Student student, GradeAirFrame gaf) {
        this.gaf = gaf;
        this.student = student;
        jsonWriter = new JsonWriter(JSON_STORE);
        this.setSize(new Dimension(400, 600));
        this.setBackground(Color.white);
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.PAGE_END;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(2, 2, 2, 2);

        makeDashboardLabel();
        makeGpaLabel(student);
        displayClasses();
        gbc.gridx = 0;
        makeAddClassButton();
        makeSaveButton();
        makeRemoveButton();
        makeSorryLabel();

        this.setVisible(true);
        gbc.weighty = 1;
        this.add(new JLabel(""), gbc);
    }

    //MODIFIES: this
    //EFFECTS: creates a label indicating an error
    private void makeSorryLabel() {
        sorry = new JLabel("Error");
        this.add(sorry);
        sorry.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: creates the user gpa label
    private void makeGpaLabel(Student student) {
        gpa = new JLabel("GPA: " + df.format(student.calculateGPA()) + "%");
        gpa.setFont(new Font(gpa.getName(), Font.PLAIN, 20));
        this.add(gpa, gbc);
    }

    //MODIFIES: this
    //EFFECTS: creates the dashboard label
    private void makeDashboardLabel() {
        dashboard = new JLabel("Dashboard");
        dashboard.setFont(new Font(dashboard.getName(), Font.BOLD, 20));
        dashboard.setHorizontalAlignment(JLabel.LEFT);
        this.add(dashboard, gbc);
    }

    //MODIFIES: this
    //EFFECTS: creates the remove toggle button, upon which user can rmeove classes when toggle is on
    private void makeRemoveButton() {
        removeButton = new JToggleButton("Remove Class");
        removeButton.addActionListener(this);
        this.add(removeButton, gbc);
    }

    //MODIFIES: this
    //EFFECTS: creates the save button so user can save their information thus far
    private void makeSaveButton() {
        saveButton = new JButton("Save");
        saveButton.setHorizontalAlignment(JButton.LEFT);
        saveButton.addActionListener(this);
        this.add(saveButton, gbc);
    }

    //MODIFIES: this
    //EFFECTS: creates the add class button so users can add a class to their current list of classes
    private void makeAddClassButton() {
        addClass = new JButton("Add");
        addClass.setHorizontalAlignment(JButton.LEFT);
        addClass.addActionListener(this);
        this.add(addClass, gbc);
    }

    //MODIFIES: this
    //EFFECTS: creates all the student classes to display in the home panel
    public void displayClasses() {
        for (int i = 0; i < student.getCourses().size(); i++) {
            Course course = student.getCourses().get(i);
            ClassPanel classPanel = new ClassPanel(course);
            if (i % 2 == 0) {
                gbc.gridx = 0;
            } else {
                gbc.gridx = 1;
            }
            this.add(classPanel, gbc);
            classPanel.addMouseListener(new ClickClassListener());
        }
    }

    //MODIFIES: this
    //EFFECTS: if user clicks a class panel, remove it if remove button is on, else open up the class information frame
    public class ClickClassListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof ClassPanel) {
                ClassPanel panel = (ClassPanel) e.getSource();
                if (!removeButton.isSelected()) {
                    new ClassInfoFrame(student, panel.getCourse());
                    gaf.dispose();
                } else {
                    student.removeCourse(panel.getCourse());
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

        //EFFECTS: change hover colour of class panels
        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() instanceof ClassPanel) {
                ClassPanel panel = (ClassPanel)e.getSource();
                panel.setBackground(new Color(0xCDCDCD));
            }
        }

        //EFFECTS: change non-hover colour of class panels
        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() instanceof ClassPanel) {
                ClassPanel panel = (ClassPanel)e.getSource();
                panel.setBackground(CLASS_PANEL_COLOR);
            }
        }
    }

    // EFFECTS: if user hits add class, bring up the form to add a class
    //          if user hits save button, save the state of their application
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
                sorry.setVisible(true);
            }
        }
    }
}

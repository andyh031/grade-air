// A popup window to change account information (first and last name)

package ui.ui.forms;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountForm extends JFrame implements ActionListener, Form {
    private static final int LABEL_WIDTH = 100;
    private static final int TEXT_WIDTH = 300;
    private static final int ONE_LINE_HEIGHT = 20;
    private static final Dimension TEXT_DIMENSION = new Dimension(TEXT_WIDTH, ONE_LINE_HEIGHT);
    private static final Dimension LABEL_DIMENSION = new Dimension(LABEL_WIDTH, ONE_LINE_HEIGHT);
    private JTextField fnameText;
    private JTextField lnameText;
    private Student student;
    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JButton submitButton;

    // EFFECTS: creates a window for first and last name fields
    public AccountForm(Student student) {
        super("Account Information");
        this.student = student;
        this.setSize(450, 600);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());

        makeFNameField(student);
        makeLNameField(student);
        makeSubmitButton();

        this.add(fnameLabel);
        this.add(fnameText);
        this.add(lnameLabel);
        this.add(lnameText);
        this.add(submitButton);
        this.setVisible(true);
    }

    //EFFECTS: creates a button to click when user is done changing information
    @Override
    public void makeSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(100, 30));
        submitButton.addActionListener(this);
    }

    //EFFECTS: creates a field for the user to change their last name
    private void makeLNameField(Student student) {
        lnameLabel = new JLabel("Last Name ");
        lnameLabel.setPreferredSize(LABEL_DIMENSION);
        lnameText = new JTextField(student.getLastName());
        lnameText.setPreferredSize(TEXT_DIMENSION);
    }

    //EFFECTS: creates a field for the user to change their first name
    private void makeFNameField(Student student) {
        fnameLabel = new JLabel("First Name");
        fnameLabel.setPreferredSize(LABEL_DIMENSION);
        fnameText = new JTextField(student.getFirstName());
        fnameText.setPreferredSize(TEXT_DIMENSION);
    }

    // MODIFIES: this
    // EFFECTS: closes current window and changes student account information
    @Override
    public void actionPerformed(ActionEvent e) {
        student.setFirstName(fnameText.getText());
        student.setLastName(lnameText.getText());
        this.dispose();
    }
}

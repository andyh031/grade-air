// Initial frame for user to either register for the application or load their pre-existing accout

package ui.gui.forms;

import model.Student;
import persistence.JsonReader;
import ui.gui.GradeAirFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LoginForm extends Form {
    private static final String JSON_STORE = "./data/student.json";

    private JLabel fnameLabel;
    private JLabel lnameLabel;
    private JLabel noAccountLabel;
    private JLabel retry;
    private JTextField userFirstName;
    private JTextField userLastName;
    private JButton loadButton;
    private JsonReader jsonReader;

    // EFFECTS: creates the login form with heading, first and last name fields, a submit and load button, and
    //          invisible labels that appear to indicate errors
    public LoginForm() {
        super("GradeAir");
        jsonReader = new JsonReader(JSON_STORE);

        makeHeading("Registration");
        firstNameField();
        lastNameField();
        makeSubmitButton();
        loadButton();
        retryLabel();
        noAccountLabel();
        this.setVisible(true);
    }

    //MODIFIES: this
    // EFFECTS: allows user to input their last name into given field to change
    private void lastNameField() {
        lnameLabel = new JLabel("Last Name");
        lnameLabel.setPreferredSize(LABEL_DIMENSION);
        userFirstName = new JTextField();
        userFirstName.setPreferredSize(TEXT_DIMENSION);
        this.add(lnameLabel);
        this.add(userFirstName);
    }

    //MODIFIES: this
    //EFFECTS: allows user to input first name into given field to change
    private void firstNameField() {
        fnameLabel = new JLabel("First Name");
        userLastName = new JTextField();
        fnameLabel.setPreferredSize(LABEL_DIMENSION);
        userLastName.setPreferredSize(TEXT_DIMENSION);
        this.add(fnameLabel);
        this.add(userLastName);
    }

    //MODIFIES: this
    //EFFECTS: loads up pre-existing account information
    public void loadButton() {
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        this.add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: a label telling user to retry
    public void retryLabel() {
        retry = new JLabel("Please retry");
        retry.setPreferredSize(FULL_WIDTH_DIMENSION);
        this.add(retry);
        retry.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: a label telling user they don't already have an account
    public void noAccountLabel() {
        noAccountLabel = new JLabel("You don't have an account");
        noAccountLabel.setPreferredSize(FULL_WIDTH_DIMENSION);
        this.add(noAccountLabel);
        noAccountLabel.setVisible(false);
    }

    //MODIFIES: student
    //EFFECTS: if user clicks submit, make a new student with the given first and last name, or
    //         tell user to retry if input is invalid
    //         if user hits load button, load the student into the application
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String fname = userFirstName.getText();
            String lname = userLastName.getText();
            if (fname.length() != 0 && lname.length() != 0) {
                student = new Student(fname, lname);
                this.dispose();
                new GradeAirFrame(student);
            } else {
                if (noAccountLabel.isVisible()) {
                    noAccountLabel.setVisible(false);
                }
                retry.setVisible(true);
            }
        } else if (e.getSource() == loadButton) {
            loadStudent();
        }
    }

    //MODIFIES: this
    //EFFECTS: loads the preexisting student into the application
    private void loadStudent() {
        try {
            student = jsonReader.read();
            this.dispose();
            new GradeAirFrame(student);
        } catch (IOException e) {
            if (retry.isVisible()) {
                retry.setVisible(false);
            }
            noAccountLabel.setVisible(true);
        }
    }

    //Getter
    public Student getStudent() {
        return this.student;
    }
}
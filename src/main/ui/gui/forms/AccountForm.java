// A popup window to change account information (first and last name)

package ui.gui.forms;

import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AccountForm extends Form {
    private JTextField fnameText;
    private JTextField lnameText;
    private JLabel fnameLabel;
    private JLabel lnameLabel;

    // EFFECTS: creates a window for first and last name fields
    public AccountForm(Student student) {
        super("Account Information");
        this.student = student;

        makeHeading("Account Information");
        makeFNameField(student);
        makeLNameField(student);
        makeSubmitButton();
        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to change their last name
    private void makeLNameField(Student student) {
        lnameLabel = new JLabel("Last Name ");
        lnameLabel.setPreferredSize(LABEL_DIMENSION);
        lnameText = new JTextField(student.getLastName());
        lnameText.setPreferredSize(TEXT_DIMENSION);
        this.add(lnameLabel);
        this.add(lnameText);
    }

    //MODIFIES: this
    //EFFECTS: creates a field for the user to change their first name
    private void makeFNameField(Student student) {
        fnameLabel = new JLabel("First Name");
        fnameLabel.setPreferredSize(LABEL_DIMENSION);
        fnameText = new JTextField(student.getFirstName());
        fnameText.setPreferredSize(TEXT_DIMENSION);
        this.add(fnameLabel);
        this.add(fnameText);
    }

    // MODIFIES: student
    // EFFECTS: closes current window and changes student account information
    @Override
    public void actionPerformed(ActionEvent e) {
        student.setFirstName(fnameText.getText());
        student.setLastName(lnameText.getText());
        this.dispose();
    }
}

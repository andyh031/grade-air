// Represents a frame showing the user their first name and last name, with a button to edit these fields

package ui.gui.forms;

import model.Student;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InformationForm extends Form {

    // EFFECTS: creates a frame showing user information with a button to prompt user to change these fields
    public InformationForm(Student student) {
        super("Account Information");
        this.student = student;

        makeHeading("Account Information");

        JLabel fname = new JLabel("First Name: " + student.getFirstName());
        fname.setPreferredSize(FULL_WIDTH_DIMENSION);

        JLabel lname = new JLabel("Last Name: " + student.getLastName());
        lname.setPreferredSize(FULL_WIDTH_DIMENSION);

        JButton submitButton = new JButton("Edit");
        submitButton.addActionListener(this);

        this.add(fname);
        this.add(lname);
        this.add(submitButton);
        this.setVisible(true);
    }

    // EFFECTS: opens up a form for user to change their first or last name
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new AccountForm(student);
    }
}

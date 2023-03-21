package ui.ui;

import model.Student;
import ui.ui.forms.AccountForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InformationFrame extends JFrame implements ActionListener {
    private Student student;

    public InformationFrame(Student student) {
        super("Account Information");
        this.student = student;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(450, 600));
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());
        this.setLayout(null);

        JLabel fname = new JLabel("First Name: " + student.getFirstName());
        fname.setBounds(5, 0, 250, 20);
        JLabel lname = new JLabel("Last Name: " + student.getLastName());
        lname.setBounds(5, 20, 250, 20);

        JButton submitButton = new JButton("Edit");
        submitButton.setBounds(5, 40, 100, 20);
        submitButton.addActionListener(this);

        this.add(fname);
        this.add(lname);
        this.add(submitButton);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        new AccountForm(student);
    }
}

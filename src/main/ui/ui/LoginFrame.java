package ui.ui;

import model.Student;
import persistence.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/student.json";

    private JLabel noAccountLabel;
    private JLabel retry;
    private JTextField userFirstName;
    private JTextField userLastName;
    private JButton submitButton;
    private JButton loadButton;
    private Student student;
    private JsonReader jsonReader;

    public LoginFrame() {
        super("GradeAir");
        jsonReader = new JsonReader(JSON_STORE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450, 600));
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());

        firstNameLabel();
        firstNameTextField();
        lastNameLabel();
        userLastNameTextField();
        submitButton();
        loadButton();
        retryLabel();
        noAccountLabel();
        this.setVisible(true);
    }

    private void lastNameLabel() {
        JLabel lnameLabel = new JLabel("Last Name");
        lnameLabel.setBounds(10, 40, 80, 20);
        this.add(lnameLabel);
    }

    private void firstNameTextField() {
        userFirstName = new JTextField(10);
        userFirstName.setBounds(100, 20, 165, 20);
        this.add(userFirstName);
    }

    private void firstNameLabel() {
        JLabel fnameLabel = new JLabel("First Name");
        fnameLabel.setBounds(10, 20, 80, 20);
        this.add(fnameLabel);
    }

    private void userLastNameTextField() {
        userLastName = new JTextField(10);
        userLastName.setBounds(100, 40, 165, 20);
        this.add(userLastName);
    }

    public void submitButton() {
        submitButton = new JButton("Submit");
        submitButton.setBounds(40, 80, 80, 20);
        submitButton.addActionListener(this);
        this.add(submitButton);
    }

    public void loadButton() {
        loadButton = new JButton("Load");
        loadButton.setBounds(150, 80, 80, 20);
        loadButton.addActionListener(this);
        this.add(loadButton);
    }

    public void retryLabel() {
        retry = new JLabel("Please retry");
        retry.setBounds(100, 100, 165, 20);
        this.add(retry);
        retry.setVisible(false);
    }

    public void noAccountLabel() {
        noAccountLabel = new JLabel("You don't have an account");
        noAccountLabel.setBounds(150, 80, 80, 20);
        this.add(noAccountLabel);
        noAccountLabel.setVisible(false);
    }

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
                if (noAccountLabel.isVisible() == true) {
                    noAccountLabel.setVisible(false);
                }
                retry.setVisible(true);
            }
        } else if (e.getSource() == loadButton) {
            loadStudent();
        }
    }

    private void loadStudent() {
        try {
            student = jsonReader.read();
            this.dispose();
            new GradeAirFrame(student);
        } catch (IOException e) {
            if (retry.isVisible() == true) {
                retry.setVisible(false);
            }
            noAccountLabel.setVisible(true);
        }
    }

    public Student getStudent() {
        return this.student;
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}

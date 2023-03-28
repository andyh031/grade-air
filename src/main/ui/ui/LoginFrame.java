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
    private GridBagConstraints gbc;

    public LoginFrame() {
        super("GradeAir");
        jsonReader = new JsonReader(JSON_STORE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450, 600));
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        firstNameLabel();
        lastNameLabel();
        submitButton();
        loadButton();
        retryLabel();
        noAccountLabel();
        this.setVisible(true);
    }

    private void lastNameLabel() {
        JLabel lnameLabel = new JLabel("Last Name");
        userFirstName = new JTextField(15);
        gbc.gridx = 0;
        this.add(lnameLabel, gbc);
        gbc.gridx = 1;
        this.add(userFirstName, gbc);
    }


    private void firstNameLabel() {
        JLabel fnameLabel = new JLabel("First Name");
        userLastName = new JTextField(15);
        gbc.gridx = 0;
        this.add(fnameLabel, gbc);
        gbc.gridx = 1;
        this.add(userLastName, gbc);
    }

    public void submitButton() {
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        gbc.gridx = 0;
        this.add(submitButton, gbc);
    }

    public void loadButton() {
        loadButton = new JButton("Load");
        loadButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(loadButton, gbc);
    }

    public void retryLabel() {
        retry = new JLabel("Please retry");
        gbc.gridx = 0;
        this.add(retry, gbc);
        retry.setVisible(false);
    }

    public void noAccountLabel() {
        noAccountLabel = new JLabel("You don't have an account");
        gbc.gridx = 0;
        this.add(noAccountLabel, gbc);
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

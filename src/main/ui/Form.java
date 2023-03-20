package ui;

import javax.swing.*;
import java.awt.*;

public class Form extends JFrame {

    public Form() {
        this.setSize(400, 600);
        this.setLayout(new GridLayout(0, 2));

        JLabel name = new JLabel("Class Name: ");
        JTextField nameText = new JTextField(20);
        nameText.setMaximumSize(new Dimension(165, 20));
        JLabel subject = new JLabel("Subject: ");
        JTextField subjectText = new JTextField(20);

        this.add(name);
        this.add(nameText);
        this.add(subject);
        this.add(subjectText);

        weightSection();

        this.setVisible(true);
    }

    public void weightSection() {
        JLabel weightLabel = new JLabel("Weighting: ");
        JTextField weightText = new JTextField(20);
        this.add(weightLabel);
        this.add(weightText);
    }


    public static void main(String[] args) {
        new Form();
    }
}

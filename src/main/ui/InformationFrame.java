package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class InformationFrame extends JFrame {
    public InformationFrame(Student student) {
        super("GradeAir");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450, 600));
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());
        this.setLayout(null);

        JLabel fname = new JLabel("First Name: " + student.getFirstName());
        fname.setBounds(5, 0, 250, 20);
        JLabel lname = new JLabel("Last Name: " + student.getLastName());
        lname.setBounds(5, 20, 250, 20);

        this.add(fname);
        this.add(lname);
        this.setVisible(true);
    }
}

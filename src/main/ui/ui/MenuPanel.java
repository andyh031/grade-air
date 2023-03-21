package ui.ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private static final int BUTTON_SIZE = 30;
    private JLabel logo;
    private JButton dashboard;
    private JButton profile;
    private Student student;

    public MenuPanel(Student student) {
        this.student = student;
        this.setSize(new Dimension(50, 1000));
        this.setBackground(new Color(0xe2e2e2));

        makeLogo();
        makeDashboard();
        makeProfile();

        this.add(logo);
        this.add(dashboard);
        this.add(profile);
    }

    private void makeProfile() {
        ImageIcon profileImage = new ImageIcon(new ImageIcon("images/profile.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        profile = new JButton();
        profile.setIcon(profileImage);
        profile.addActionListener(e -> new InformationFrame(student));
    }

    private void makeDashboard() {
        ImageIcon dashboardImage = new ImageIcon(new ImageIcon("images/dashboard.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        dashboard = new JButton();
        dashboard.setIcon(dashboardImage);
    }

    private void makeLogo() {
        ImageIcon logoImage = new ImageIcon(new ImageIcon("images/logo.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        logo = new JLabel();
        logo.setIcon(logoImage);
    }


}

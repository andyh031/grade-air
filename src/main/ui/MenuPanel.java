package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private static final int BUTTON_SIZE = 30;
    private JLabel logo;
    private JButton dashboard;
    private JButton profile;

    public MenuPanel(Student student) {
        this.setSize(new Dimension(50, 1000));
        this.setBackground(new Color(0xe2e2e2));
        ImageIcon logoImage = new ImageIcon(new ImageIcon("images/logo.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        ImageIcon profileImage = new ImageIcon(new ImageIcon("images/profile.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        ImageIcon dashboardImage = new ImageIcon(new ImageIcon("images/dashboard.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));

        logo = new JLabel();
        dashboard = new JButton();
        profile = new JButton();

        logo.setIcon(logoImage);
        dashboard.setIcon(dashboardImage);
        profile.setIcon(profileImage);

        profile.addActionListener(e -> new InformationFrame(student));

        this.add(logo);
        this.add(dashboard);
        this.add(profile);
    }
}

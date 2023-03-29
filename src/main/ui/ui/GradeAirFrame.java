//EFFECTS: creates the grade air application home screen, with a menu panel on the side and a home panel with classes

package ui.ui;

import model.Student;

import javax.swing.*;
import java.awt.*;

public class GradeAirFrame extends JFrame {
    private JPanel homePanel;
    private JPanel menuPanel;

    // EFFECTS: generates the home screen
    public GradeAirFrame(Student student) {
        super("GradeAir");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450, 600));
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());

        menuPanel = new MenuPanel(student);
        homePanel = new HomePanel(student, this);

        this.add(menuPanel);
        this.add(homePanel);
        this.setVisible(true);
    }
}

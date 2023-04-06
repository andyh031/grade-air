//EFFECTS: creates the grade air application home screen, with a menu panel on the side and a home panel with classes

package ui.gui;

import events.Event;
import events.EventLog;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GradeAirFrame extends JFrame implements WindowListener {
    private JPanel homePanel;
    private JPanel menuPanel;

    // EFFECTS: generates the home screen
    public GradeAirFrame(Student student) {
        super("GradeAir");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(this);
        this.setSize(new Dimension(450, 600));
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());

        menuPanel = new MenuPanel(student);
        homePanel = new HomePanel(student, this);

        this.add(menuPanel);
        this.add(homePanel);
        this.setVisible(true);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

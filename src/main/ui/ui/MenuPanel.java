// Represents the menu panel on the left hand side for the user to view their account information

package ui.ui;

import model.Student;
import ui.ui.forms.AccountForm;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPanel extends JPanel {
    private static final int BUTTON_SIZE = 35;
    private JLabel logo;
    private JLabel profile;
    private Student student;
    private static final Border border = BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK);

    // EFFECTS: Creates a menu panel with the application logo and account information
    public MenuPanel(Student student) {
        this.student = student;
        this.setBorder(border);
        this.setSize(new Dimension(50, 1000));
        this.setBackground(new Color(0xFFFFFF));

        makeLogo();
        makeProfile();

        this.add(logo);
        this.add(profile);
    }

    private void makeLogo() {
        ImageIcon logoImage = new ImageIcon(new ImageIcon("images/logo.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        logo = new JLabel();
        logo.setIcon(logoImage);
    }

    private void makeProfile() {
        ImageIcon profileImage = new ImageIcon(new ImageIcon("images/profile.png")
                .getImage().getScaledInstance(BUTTON_SIZE, BUTTON_SIZE, Image.SCALE_DEFAULT));
        profile = new JLabel();
        profile.setIcon(profileImage);
        profile.addMouseListener(new ClickProfileListener());
    }

    public class ClickProfileListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == profile) {
                new AccountForm(student);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == profile) {
                profile.setOpaque(true);
                profile.setBackground(new Color(0xCDCDCD));
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == profile) {
                profile.setBackground(Color.WHITE);
            }
        }
    }


}

// Abstract class representing a general form (where users must input fields)

package ui.gui.forms;

import model.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Form extends JFrame implements ActionListener {
    protected static final int ONE_LINE_HEIGHT = 20;
    protected static final int LABEL_WIDTH = 100;
    protected static final int TEXT_WIDTH = 300;
    protected static final Dimension TEXT_DIMENSION = new Dimension(TEXT_WIDTH, ONE_LINE_HEIGHT);
    protected static final Dimension LABEL_DIMENSION = new Dimension(LABEL_WIDTH, ONE_LINE_HEIGHT);
    protected static final Dimension FULL_WIDTH_DIMENSION = new Dimension(LABEL_WIDTH + TEXT_WIDTH, ONE_LINE_HEIGHT);
    protected JLabel heading;
    protected JButton submitButton;
    protected Student student;

    // EFFECTS: creates a form frame and sets its logo to the application logo
    public Form(String str) {
        super(str);
        this.setSize(450, 600);
        ImageIcon image = new ImageIcon("images/logo.png");
        this.setIconImage(image.getImage());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());
    }

    //MODIFIES: this
    // EFFECTS: adds a heading to the form
    public void makeHeading(String str) {
        heading = new JLabel(str);
        heading.setFont(new Font(heading.getName(), Font.BOLD, 15));
        heading.setPreferredSize(new Dimension(420, ONE_LINE_HEIGHT));
        this.add(heading);
    }

    //MODIFIES: this;
    // EFFECTS: creates a button for user to 'submit' the form
    public void makeSubmitButton() {
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        this.add(submitButton);
    }
}

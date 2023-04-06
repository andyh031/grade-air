// Runs the GradeAir application

package ui;

import events.Event;
import events.EventLog;
import ui.gui.forms.LoginForm;

public class Main {
//    public static void main(String[] args) {
//        GradeAir gradeAir = new GradeAir();
//        gradeAir.login();
//        gradeAir.end();
//
    public static void main(String[] args) {
        new LoginForm();
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString());
        }
    }
}
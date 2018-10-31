package Project.Helpers;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Helper Clock class
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class Clock extends Thread {

    private JLabel clock;

    public Clock(JLabel clock) {
        this.clock = clock;
    }

    @Override
    public void run() {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("HH:mm:ss | E dd MMMM yyyy");
        Date date;

        while (true) {
            date = new Date();

            clock.setText(dateFormat.format(date));

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Helpers/Clock.java run() log: " + e);
            }
        }
    }

}

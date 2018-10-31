package Project.Helpers;

import javax.swing.*;
import java.awt.*;


/**
 * Helper Error Dialog class
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class ErrorDialog extends JOptionPane {

    public static void showMessage(String message, Window window) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        JDialog dialog = optionPane.createDialog("Error!");

        int xPosition =  window.getLocationOnScreen().x + (window.getWidth() / 2) - (dialog.getWidth() / 2);
        int yPosition =  window.getLocationOnScreen().y + (window.getHeight() / 2) - (dialog.getHeight() / 2);

        dialog.setLocation(xPosition, yPosition);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

}

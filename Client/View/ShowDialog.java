package Project.Client.View;

import Project.Helpers.ErrorDialog;
import Project.Helpers.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;


/**
 * Show Dialog class
 * Display single event data
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class ShowDialog extends JDialog {

    /**
     * Reference to Main Client View class
     */
    private ClientView clientView;

    /**
     * Initialize function button and fields
     */
    private JLabel[] fields = new JLabel[Event.FIELD_COUNT];


    /**
     * Show Dialog constructor
     * Create dialog and initialize fields
     *
     * @param clientView ClientView
     */
    ShowDialog(ClientView clientView) {
        this.clientView = clientView;

        this.setSize(600, 300);
        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(101, 79, 161));
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel showPanel = new JPanel();

        showPanel.setLayout(new GridLayout(Project.Helpers.Event.FIELD_COUNT, 1, 5, 5));
        showPanel.setPreferredSize(new Dimension(550, 250));


        for (int i = 0; i < Event.FIELD_COUNT; i++) {
            JLabel label = new JLabel(EditDialog.LABELS[i]);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            showPanel.add(label);
            this.fields[i] = new JLabel();
            this.fields[i].setForeground(Color.WHITE);
            this.fields[i].setFont(new Font("Arial", Font.BOLD, 14));
            showPanel.add(fields[i]);
        }

        LineBorder lineBorder = new LineBorder(Color.WHITE, 2);
        TitledBorder border = new TitledBorder(
                lineBorder,
                "Show event",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 16)
        );
        border.setTitleColor(Color.WHITE);

        showPanel.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(10, 10, 10, 10)));
        showPanel.setBackground(new Color(101, 79, 161));
        showPanel.setForeground(Color.WHITE);

        this.add(showPanel);
    }


    /**
     * Input data from single Event into dialog
     *
     * @param index Integer
     */
    void showEvent(int index) {
        if (index >= 0) {
            Event event = clientView.events.get(index);

            for (int i = 0; i < fields.length; i++) {
                this.fields[i].setText(event.get(i));
            }

            this.setPosition();
            this.setVisible(true);
        } else {
            ErrorDialog.showMessage("To show an event, select one from the list", clientView);
        }
    }


    /**
     * Set position relative to Client Application
     */
    private void setPosition() {
        Point point = clientView.getLocationOnScreen();
        this.setLocation(point.x + 150, point.y + 100);
    }

}

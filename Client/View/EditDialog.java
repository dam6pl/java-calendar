package Project.Client.View;

import Project.Helpers.ErrorDialog;
import Project.Helpers.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Edit Dialog class
 * Class supporting events CRUD
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class EditDialog extends JDialog implements ActionListener {

    /**
     * Reference to Main Client View class
     */
    private ClientView clientView;

    /**
     * List of event labels.
     */
    final static String[] LABELS = {
            "Event name",
            "Event description",
            "Event location",
            "Event date (yyyy-mm-dd)",
            "Event time (hh:mm)"
    };


    /**
     * Initialize function button and fields
     */
    private JButton saveSettings = new JButton("Save settings");
    private JTextField[] fields = new JTextField[Event.FIELD_COUNT];


    /**
     * Edit Dialog constructor
     * Create dialog for event CRUD
     *
     * @param clientView ClientView
     */
    EditDialog(ClientView clientView) {
        this.clientView = clientView;

        this.setSize(600, 350);
        this.setLayout(new FlowLayout());
        this.getContentPane().setBackground(new Color(101, 79, 161));
        this.setResizable(false);

        JPanel editPanel = new JPanel();

        editPanel.setLayout(new GridLayout(Event.FIELD_COUNT, 1, 5, 5));
        editPanel.setPreferredSize(new Dimension(550, 250));


        for (int i = 0; i < Event.FIELD_COUNT; i++) {
            JLabel label = new JLabel(LABELS[i]);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            editPanel.add(label);
            this.fields[i] = new JTextField();
            editPanel.add(fields[i]);
        }

        LineBorder lineBorder = new LineBorder(Color.WHITE, 2);
        TitledBorder border = new TitledBorder(
                lineBorder,
                "Edit event",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD,  16)
        );
        border.setTitleColor(Color.WHITE);

        editPanel.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(10, 10, 10, 10)));
        editPanel.setBackground(new Color(101, 79, 161));
        editPanel.setForeground(Color.WHITE);

        this.add(editPanel);

        saveSettings.setFont(new Font("Arial", Font.BOLD,  15));
        saveSettings.setPreferredSize(new Dimension(150, 50));

        this.add(saveSettings);
        saveSettings.addActionListener(this);
    }


    /**
     * Override method for ActionListener
     *
     * @param event ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Object object = event.getSource();

        if (object == saveSettings) {
            this.saveSettings();
        }
    }


    /**
     * Recording event data from the form in the event list
     */
    private void saveSettings() {
        String[] data = new String[Event.FIELD_COUNT];

        for (int i = 0; i < data.length; i++) {
            String text = fields[i].getText();

            if (text.equals("")) {
                ErrorDialog.showMessage("All fields must be filled out!", this);
                return;
            }
            data[i] = fields[i].getText();
        }

        int index = clientView.eventsList.list.getSelectedIndex();

        if (index >= 0) {
            clientView.events.set(index, new Event(data));
        } else {
            clientView.events.add(new Event(data));
        }

        clientView.eventsList.updateEventsList();

        this.clientView.serverConnection.sendEventsToServer(this.clientView.events);

        this.dispose();
    }

    /**
     * Initialize dialog for adding new Event
     */
    void addEvent() {
        clientView.eventsList.list.clearSelection();

        this.resetFields();
        this.setPosition();
        this.setVisible(true);
    }


    /**
     * Initialize dialog with data based on selected event
     *
     * @param index Integer
     */
    void editEvent(int index) {
        if (index >= 0) {
            Event event = clientView.events.get(index);

            for (int i = 0; i < fields.length; i++) {
                this.fields[i].setText(event.get(i));
            }

            this.setPosition();
            this.setVisible(true);
        } else {
            ErrorDialog.showMessage("To edit an event, select one from the list", clientView);
        }
    }


    /**
     * Remove data from dialog
     */
    private void resetFields() {
        for (int i = 0; i < Event.FIELD_COUNT; i++) {
            this.fields[i].setText("");
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

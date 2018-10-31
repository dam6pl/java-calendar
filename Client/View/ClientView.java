package Project.Client.View;

import Project.Client.Client;
import Project.Helpers.Clock;
import Project.Helpers.Event;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * Client View class
 * The view of the Client application and support buttons events
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class ClientView extends JFrame implements ActionListener {

    /**
     * Reference to Main Client class
     */
    Client serverConnection;

    /**
     * Initialize the Events list
     */
    ArrayList<Event> events = new ArrayList<>();

    /**
     * References to classes supports view and edit events
     */
    EventsList eventsList = new EventsList(this);
    EditDialog editDialog = new EditDialog(this);
    ShowDialog showDialog = new ShowDialog(this);

    /**
     * Initialize function buttons
     */
    private JButton addEvent = new JButton("Add Event");
    private JButton editEvent = new JButton("Edit Event");
    private JButton removeEvent = new JButton("Remove Event");


    /**
     * Client View constructor
     * Creating an application view and downloading events from the server
     *
     * @param serverConnection Client
     */
    public ClientView(Client serverConnection) {
        super("Java Client-Server Event Calendar 2018");

        this.serverConnection = serverConnection;

        this.events = this.serverConnection.getEventsFromServer();

        this.add(renderNavigation(), BorderLayout.WEST);
        this.add(eventsList, BorderLayout.CENTER);

        this.add(renderFooter(), BorderLayout.SOUTH);

        this.setLocation(50, 50);
        this.setSize(900, 600);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        eventsList.updateEventsList();
    }


    /**
     * Render the navigation part of application view
     *
     * @return JPanel
     */
    private JPanel renderNavigation() {
        JPanel navigation = new JPanel();

        navigation.setLayout(new GridLayout(8, 1));
        navigation.setBorder(new EmptyBorder(10, 10, 10, 10));
        navigation.setBackground(new Color(101, 79, 161));

        navigation.add(addEvent);
        navigation.add(editEvent);
        navigation.add(removeEvent);

        Font buttonFont = new Font("Arial", Font.BOLD,  15);
        addEvent.setFont(buttonFont);
        editEvent.setFont(buttonFont);
        removeEvent.setFont(buttonFont);

        addEvent.addActionListener(this);
        editEvent.addActionListener(this);
        removeEvent.addActionListener(this);

        return navigation;
    }


    /**
     * Render the footer part of application view
     *
     * @return JPanel
     */
    private JPanel renderFooter() {
        JPanel footer = new JPanel(new GridLayout(1, 2));

        JLabel clock = new JLabel();
        clock.setForeground(Color.WHITE);
        footer.add(clock);

        Clock clockThread = new Clock(clock);
        clockThread.start();

        footer.setBorder(new EmptyBorder(10, 10, 10, 10));
        footer.setBackground(new Color(101, 79, 161));

        JLabel author = new JLabel("Author: Damian Nowak (damiannowak42@gmail.com)");
        author.setHorizontalAlignment(JLabel.RIGHT);
        author.setForeground(Color.WHITE);
        footer.add(author);

        return footer;
    }


    /**
     * Override method for ActionListener
     *
     * @param event ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        Object object = event.getSource();

        if (object == addEvent) {
            this.editDialog.addEvent();
        } else if (object == editEvent) {
            this.editDialog.editEvent(eventsList.list.getSelectedIndex());
        } else if (object == removeEvent) {
            this.eventsList.removeEvent(eventsList.list.getSelectedIndex());
            this.serverConnection.sendEventsToServer(this.events);
        }
    }

}

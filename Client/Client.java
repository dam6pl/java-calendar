package Project.Client;

import Project.Client.View.ClientView;
import Project.Helpers.Event;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Client class
 * Connection with Server, and receiving and sending Events from Server
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class Client {

    /**
     * Server host and post
     */
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 2020;

    /**
     * Client socket
     */
    private Socket socket;

    /**
     * Input and output socket stream
     */
    private ObjectOutputStream out;
    private ObjectInputStream in;


    /**
     * Client constructor
     * Creation of a socket, connection to the server and initialize Client View.
     */
    public Client() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Client/Client.java log: Client run successful!");
        } catch (Exception e) {
            System.out.println("Client/Client.java log: Run server before start client!");
            System.exit(1);
        }

        this.run();

        new ClientView(this);
    }

    /**
     * Initialize input and output streams
     */
    private void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            System.out.println("Client/Client.java run() log: " + e);
        }
    }

    /**
     * Downloads the list of events from the server
     *
     * @return ArrayList
     */
    public ArrayList<Event> getEventsFromServer() {
        ArrayList<Event> events = new ArrayList<>();

        try {
            Object object = in.readObject();
            events = (ArrayList<Event>) object;
        } catch (Exception e) {
            System.out.println("Client/Client.java getEventsFromServer() log: " + e);
        }

        return events;
    }


    /**
     * Send the list of events to the server
     *
     * @param events ArrayList
     */
    public void sendEventsToServer(ArrayList<Event> events) {
        try {
            out.writeObject(events);
            out.reset();
        } catch (Exception e) {
            System.out.println("Client/Client.java getEventsFromServer() log: " + e);
        }
    }

}
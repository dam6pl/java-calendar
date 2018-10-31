package Project.Server;

import Project.Helpers.Event;
import Project.Helpers.FileInputOutput;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Server class
 * Main Server side of application, with initialization of sockets
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class Server {

    /**
     * Server port
     */
    public static final int SERVER_PORT = 2020;

    /**
     * Server socket
     */
    private ServerSocket serverSocket;

    /**
     * List of events
     */
    ArrayList<Event> events;


    /**
     * Server constructor
     * Create Server socket and import Events from the file
     */
    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server/Server.java constructor log: Server run successful!");
        } catch (Exception e) {
            System.out.println("Server/Server.java constructor log: Can't create a socket!");
            System.exit(1);
        }

        events = FileInputOutput.ReadFile("events.data");

        this.run();
    }


    /**
     * Create connection with clients using Client Services class
     */
    private void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Server/Server.java run() log: New client from "
                        + socket.getInetAddress() + ":" + socket.getPort());
                new ClientServices(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Server/Server.java run() log: " + e);
        }
    }

}

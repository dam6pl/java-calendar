package Project.Server;

import Project.Helpers.Event;
import Project.Helpers.FileInputOutput;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Client Services class
 * Class support connection with the single client
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class ClientServices {

    /**
     * Reference to Server
     */
    private Server server;

    /**
     * Socket for current client
     */
    private Socket socket;

    /**
     * Input and output streams
     */
    private ObjectOutputStream out;
    private ObjectInputStream in;


    /**
     * Client Services constructor
     * Initialize references to server and open streams
     *
     * @param server Server
     * @param socket Socket
     */
    ClientServices(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;

        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Server/ClientServices.java constructor log: " + e);
        }

        this.run();
    }

    /**
     * Start connection with the single client
     * After first connection send Events
     */
    public void run() {
        try {
            out.writeObject(server.events);

            while (true) {
                Object object = in.readObject();
                server.events = (ArrayList<Event>) object;

                FileInputOutput.SaveFile("events.data", server.events);
            }
        } catch (EOFException e) {
            try {
                socket.close();
                System.out.println("Server/ClientServices.java run() log: Client disconnected!");
            } catch (IOException e1) {
                System.out.println("Server/ClientServices.java run() log: " + e1);
            }
        } catch (Exception e) {
            System.out.println("Server/ClientServices.java run() log: " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Server/ClientServices.java run() log: " + e);
            }
        }
    }

}

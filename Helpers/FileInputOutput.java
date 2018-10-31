package Project.Helpers;

import java.io.*;
import java.util.ArrayList;


/**
 * Helper File Input and Output class
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class FileInputOutput {

    /**
     * Relative path to directory with saved files
     */
    public static final String RELATIVE_PATH = System.getProperty("user.dir") + "/src/Project/Files/";


    /**
     * Read List of events from the File
     *
     * @param fileName String
     * @return ArrayList
     */
    public static ArrayList<Event> ReadFile(String fileName) {
        ArrayList<Event> events = new ArrayList<>();
        DataInputStream file = null;

        try {
            file = new DataInputStream(
                    new FileInputStream(FileInputOutput.RELATIVE_PATH + fileName)
            );

            while (true) {
                String[] eventData = new String[Event.FIELD_COUNT];

                for (int i = 0; i < Event.FIELD_COUNT; i++) {
                    eventData[i] = file.readUTF();
                }

                events.add(new Event(eventData));
            }
        } catch (EOFException e) {
            try {
                file.close();
            } catch (IOException e1) {
                System.out.println(e1);
            }
        } catch (IOException e) {
            System.out.println("Helpers/FileInputOutput.java ReadFile() log: " + e);
        }

        return events;
    }


    /**
     * Save tje List of events in the File
     *
     * @param fileName String
     * @param events ArrayList
     */
    public static void SaveFile(String fileName, ArrayList<Event> events) {
        try {
            DataOutputStream out = new DataOutputStream(
                    new FileOutputStream(FileInputOutput.RELATIVE_PATH + fileName)
            );

            for (Event event : events) {
                for (int j = 0; j < Event.FIELD_COUNT; j++) {
                    out.writeUTF(event.get(j));
                }
            }

            out.close();
        } catch (Exception e) {
            System.out.println("Helpers/FileInputOutput.java SaveFile() log: " + e);
        }
    }

}

package Project.Helpers;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Helper Event class
 *
 * @version 1.0
 * @author Damian Nowak
 */
public class Event implements Serializable {

    /**
     * Count of fields in the single Event
     */
    public static final int FIELD_COUNT = 5;

    /**
     * Array with Events fields
     */
    private HashMap<Integer, String> data = new HashMap<Integer, String>();


    /**
     * Event constructor
     * Save String array with data in internal data array
     *
     * @param dataArray String[]
     */
    public Event(String[] dataArray) {
        for (int i = 0; i < FIELD_COUNT; i++) {
            data.put(i, dataArray[i]);
        }
    }

    /**
     * Get single value from Event data
     *
     * @param filedNumber integer
     *
     * @return String
     */
    public String get(int filedNumber) {
        return data.get(filedNumber);
    }


    /**
     * Override toString method
     *
     * @return string
     */
    @Override
    public String toString() {
        return "  " + get(3) + " " + get(4) + " | " + get(0) + " | " + get(2);
    }

}

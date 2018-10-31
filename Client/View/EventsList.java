package Project.Client.View;

import Project.Helpers.ErrorDialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Events list class
 * Class displaying list of events in Client application
 *
 * @version 1.0
 * @author Damian Nowak
 */
class EventsList extends JPanel {

    /**
     * Reference to Main Client View class
     */
    private ClientView clientView;

    /**
     * Initialize list of Events
     */
    JList list = new JList();


    /**
     * Events list constructor
     * Create view of Events list
     *
     * @param clientView ClientView
     */
    EventsList(ClientView clientView) {
        this.clientView = clientView;

        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setBackground(new Color(101, 79, 161));

        LineBorder lineBorder = new LineBorder(Color.WHITE, 2);
        TitledBorder border = new TitledBorder(
                lineBorder,
                "Events list",
                TitledBorder.LEFT,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD,  16)
        );
        border.setTitleColor(Color.WHITE);

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setBorder(BorderFactory.createCompoundBorder(border, new EmptyBorder(10, 10, 10, 10)));
        list.setBackground(new Color(101, 79, 161));
        list.setForeground(Color.WHITE);
        list.setFont(new Font("Arial", Font.PLAIN,  15));
        list.setFixedCellHeight(30);
        list.setSelectionBackground(Color.WHITE);
        list.setSelectionForeground(new Color(101, 79, 161));
        list.setFocusable(true);

        this.add(list);
        this.updateEventsList();
        this.mouseListener(list);
    }


    private void mouseListener(JList list) {
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    clientView.showDialog.showEvent(list.getSelectedIndex());
                }
            }
        });
    }

    /**
     * Remove single Event from the list
     *
     * @param index Integer
     */
    void removeEvent(int index) {
        if (index >= 0) {
            clientView.events.remove(index);
        } else {
            ErrorDialog.showMessage("To remove an event, select one from the list", clientView);
        }
        updateEventsList();
    }


    /**
     * Update Events list after CRUD operations
     */
    void updateEventsList() {
        list.setListData(clientView.events.toArray());
    }
}

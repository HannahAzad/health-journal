package ui;

import model.EventLog;
import model.Event;
import model.HealthJournal;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;


// EFFECTS: creates a menu panel, allowing users to add entry, view entries, or save & quit
public class MenuPanel extends JFrame implements ActionListener {
    private HealthJournal healthJournal;
    private static final String JSON_STORE = "./data/healthjournal.json";
    private JsonWriter writer;
    private EventLog eventLog;

    // MODIFIES: this
    // EFFECTS: constructs panel, makes visible, adds menu elements
    public MenuPanel(HealthJournal healthJournal) {
        writer = new JsonWriter(JSON_STORE);
        this.healthJournal = healthJournal;
        eventLog = EventLog.getInstance();

        this.setTitle("My Health Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(400, 200));

        JPanel panel = (JPanel) getContentPane();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(0,1));

        addMenuButton("Add a New Entry");
        addMenuButton("View Your Past Entries");
        addMenuButton("Save Journal & Quit");

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // REQUIRES: title is non-null
    // EFFECTS: adds button with the given string to panel
    public void addMenuButton(String title) {
        JButton menuButton = new JButton(title);
        menuButton.addActionListener(this);
        add(menuButton);
    }

    // REQUIRES: button click
    // EFFECTS: processes the user's button click
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add a New Entry")) {
            NewEntryPage newEntry = new NewEntryPage(healthJournal);
            this.setVisible(false);
        }
        if (e.getActionCommand().equals(("View Your Past Entries"))) {
            if (healthJournal.getJournalEntries().size() == 0) {
                JOptionPane.showMessageDialog(this, "You have no existing entries.");
            } else {
                ViewPastEntries viewPastEntries = new ViewPastEntries(healthJournal);
                this.setVisible(false);
            }
        }
        if (e.getActionCommand().equals("Save Journal & Quit")) {
            try {
                saveAndQuit();
            } catch (IOException exception) {
                throw new RuntimeException();
            }
        }
    }

    public void printEventLog() {
        for (Event event : this.eventLog) {
            System.out.println(event);
        }
    }

    // EFFECTS: saves health journal to file
    public void saveAndQuit() throws FileNotFoundException {
        writer.open();
        writer.write(healthJournal);
        writer.close();
        printEventLog();
        eventLog.clear();

        System.exit(0);
    }
}

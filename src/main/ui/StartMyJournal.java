package ui;

import model.HealthJournal;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// EFFECTS: creates a StartMyJournal panel, allowing users to start a journal or load from file
public class StartMyJournal extends JFrame implements ActionListener {
    private HealthJournal healthJournal;
    private static final String JSON_STORE = "./data/healthjournal.json";
    private JsonReader reader;

    // MODIFIES: this
    // EFFECTS: constructs panel visible panel, adds elements to panel
    public StartMyJournal() {
        reader = new JsonReader(JSON_STORE);

        this.setTitle("Health Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(350, 250));

        JPanel panel = (JPanel) getContentPane();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(0,1));

        addStartingButton("Create a New Journal");
        addStartingButton("Load Existing Journal");

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // REQUIRES: title is non-null
    // MODIFIES: this
    // EFFECTS: adds button with the given string
    public void addStartingButton(String title) {
        JButton menuButton = new JButton(title);
        menuButton.addActionListener(this);
        add(menuButton);
    }

    // EFFECTS: processes the user's button activity
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Create a New Journal")) {
            MenuPanel menu = new MenuPanel(new HealthJournal());
            this.setVisible(false);
        }
        if (e.getActionCommand().equals(("Load Existing Journal"))) {
            try {
                healthJournal = reader.read();
                MenuPanel menu = new MenuPanel(healthJournal);
                this.setVisible(false);
            } catch (IOException i) {
                throw new RuntimeException(i);
            }
        }
    }
}

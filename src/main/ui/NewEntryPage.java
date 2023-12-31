package ui;

import model.Entry;
import model.HealthJournal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// EFFECTS: opens a new entry panel, user can save their responses on this panel to add a new entry
public class NewEntryPage extends JFrame implements ActionListener {
    private final HealthJournal healthJournal;
    private JTextField idAnswer;
    private JTextField sleepAnswer;
    private JTextField mealsAnswer;
    private JTextField activityAnswer;
    private JTextField moodAnswer;

    // MODIFIES: this
    // EFFECTS: constructs panel for a new entry with images, buttons, and text fields
    public NewEntryPage(HealthJournal healthJournal) {
        this.healthJournal = healthJournal;

        this.setTitle("My Health Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 600));

        JPanel panel = (JPanel) getContentPane();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(0,1));

        initializeEntryFields();

        JButton saveEntry = new JButton("Save Entry");
        saveEntry.addActionListener(this);
        saveEntry.setBackground(Color.GREEN);
        add(saveEntry);
        JButton discardEntry = new JButton("Discard Entry");
        discardEntry.addActionListener(this);
        discardEntry.setBackground(Color.PINK);
        add(discardEntry);

        pack();
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    // EFFECTS: creates the label and text fields, adds them to the panel
    public void initializeEntryFields() {
        JLabel idQuestion = new JLabel("Today's date in the format MMDDYYYY:");
        idAnswer = new JTextField();
        JLabel sleepQuestion = new JLabel("How many hours of sleep did you get last night?");
        sleepAnswer = new JTextField();
        JLabel mealsQuestion = new JLabel("How many meals did you eat today?");
        mealsAnswer = new JTextField();
        JLabel activityQuestion = new JLabel("How many minutes of physical activity did you engage in today?");
        activityAnswer = new JTextField();
        JLabel moodQuestion = new JLabel("On a scale of 1-5, how would you rate your overall mood today?");
        moodAnswer = new JTextField();

        add(idQuestion);
        add(idAnswer);
        add(sleepQuestion);
        add(sleepAnswer);
        add(mealsQuestion);
        add(mealsAnswer);
        add(activityQuestion);
        add(activityAnswer);
        add(moodQuestion);
        add(moodAnswer);
    }

    // REQUIRES: integer values in text fields
    // EFFECTS: creates a new entry from the values in the text fields
    public Entry newEntryFromResponses() {
        try {
            int id = Integer.parseInt(idAnswer.getText());
            int sleep = Integer.parseInt(sleepAnswer.getText());
            int food = Integer.parseInt(mealsAnswer.getText());
            int activity = Integer.parseInt(activityAnswer.getText());
            int mood = Integer.parseInt(moodAnswer.getText());
            return new Entry(id, sleep, food, activity, mood);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this, "Invalid answer. Please respond with a numerical value");
        }
        return null;
    }

    // REQUIRES: button click
    // EFFECTS: processes the user's button click
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save Entry")) {
            Entry newEntry = newEntryFromResponses();
            healthJournal.addNewEntry(newEntry);
            healthJournal.addNewEntryID(newEntry);
            MenuPanel menuPanel = new MenuPanel(healthJournal);
            this.setVisible(false);
        }
        if (e.getActionCommand().equals(("Discard Entry"))) {
            MenuPanel menuPanel = new MenuPanel(healthJournal);
            this.setVisible(false);
        }
    }

}

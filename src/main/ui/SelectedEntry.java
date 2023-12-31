package ui;

import model.Entry;
import model.HealthJournal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// displays the entry that the user selected from ViewPastEntries
public class SelectedEntry extends JFrame implements ActionListener {
    private final HealthJournal healthJournal;
    private final Entry entry;
    private JLabel entryImage;
    private JLabel sleepImage;
    private JLabel mealsImage;
    private JLabel activityImage;
    private JLabel moodImage;

    // MODIFIES: this
    // EFFECTS: creates a panel with the entry details of the entry with the given entry ID
    public SelectedEntry(HealthJournal healthJournal, Integer entryID) {
        this.healthJournal = healthJournal;
        this.entry = healthJournal.getEntryFromID(entryID);

        this.setTitle("My Health Journal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));

        JPanel panel = (JPanel) getContentPane();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridLayout(0,2));

        initializeIDImage();
        initializeSleepImage();
        initializeMealsImage();
        initializeActivityImage();
        initializeMoodImage();

        displayEntryDetails();
        addButtons();

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // EFFECTS: adds the entry details and images to the panel
    public void displayEntryDetails() {
        JLabel entryID = new JLabel("Date of Entry: "
                + entry.getEntryID());
        JLabel sleep = new JLabel("Hours Slept: "
                + entry.getSleepAnswer());
        JLabel food = new JLabel("Number of Meals Eaten: "
                + entry.getFoodAnswer());
        JLabel activity = new JLabel("Minutes of Physical Activity: "
                + entry.getActivityAnswer());
        JLabel mood = new JLabel("Mood Rated on a Scale of 1-5: "
                + entry.getMoodAnswer());

        add(entryImage);
        add(entryID);
        add(sleepImage);
        add(sleep);
        add(mealsImage);
        add(food);
        add(activityImage);
        add(activity);
        add(moodImage);
        add(mood);
    }

    // MODIFIES: this
    // EFFECTS: initializes entryImage
    public void initializeIDImage() {
        ImageIcon entryIcon = new ImageIcon("data/entryImage.png");
        Image entryImageResized = entryIcon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon entryIconResized = new ImageIcon(entryImageResized);
        JLabel entryLabel = new JLabel();
        entryLabel.setIcon(entryIconResized);
        entryLabel.setPreferredSize(new Dimension(30,30));
        this.entryImage = entryLabel;
    }

    // MODIFIES: this
    // EFFECTS: initializes sleepImage
    public void initializeSleepImage() {
        ImageIcon sleepIcon = new ImageIcon("data/sleepImage.png");
        Image sleepImageResized = sleepIcon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon sleepIconResized = new ImageIcon(sleepImageResized);
        JLabel sleepLabel = new JLabel();
        sleepLabel.setIcon(sleepIconResized);
        sleepLabel.setPreferredSize(new Dimension(30,30));
        this.sleepImage = sleepLabel;
    }

    // MODIFIES: this
    // EFFECTS: initializes mealsImage
    public void initializeMealsImage() {
        ImageIcon mealsIcon = new ImageIcon("data/mealsImage.png");
        Image mealsImageResized = mealsIcon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon mealsIconResized = new ImageIcon(mealsImageResized);
        JLabel mealsLabel = new JLabel();
        mealsLabel.setIcon(mealsIconResized);
        mealsLabel.setPreferredSize(new Dimension(30,30));
        this.mealsImage = mealsLabel;
    }

    // MODIFIES: this
    // EFFECTS: initializes activityImage
    public void initializeActivityImage() {
        ImageIcon activityIcon = new ImageIcon("data/activityImage.png");
        Image activityImageResized = activityIcon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon activityIconResized = new ImageIcon(activityImageResized);
        JLabel activityLabel = new JLabel();
        activityLabel.setIcon(activityIconResized);
        activityLabel.setPreferredSize(new Dimension(30,30));
        this.activityImage = activityLabel;
    }

    // MODIFIES: this
    // EFFECTS: initializes moodImage
    public void initializeMoodImage() {
        ImageIcon moodIcon = new ImageIcon("data/moodImage.png");
        Image moodImageResized = moodIcon.getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon moodIconResized = new ImageIcon(moodImageResized);
        JLabel moodLabel = new JLabel();
        moodLabel.setIcon(moodIconResized);
        moodLabel.setPreferredSize(new Dimension(30,30));
        this.moodImage = moodLabel;
    }

    // EFFECTS: adds the delete entry, back, and menu buttons to the panel
    public void addButtons() {
        JButton delete = new JButton("Delete Entry");
        delete.addActionListener(this);
        JButton back = new JButton("Back");
        back.addActionListener(this);
        back.setBackground(Color.PINK);
        JButton menu = new JButton("Menu");
        menu.addActionListener(this);
        menu.setBackground(Color.MAGENTA);

        add(delete);
        add(back);
        add(menu);
    }

    // REQUIRES: button click
    // EFFECTS: processes the user's button click
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Delete Entry")) {
            healthJournal.removeEntry(entry);
            MenuPanel menuPanel = new MenuPanel(healthJournal);
            this.setVisible(false);
        }

        if (e.getActionCommand().equals("Back")) {
            ViewPastEntries viewPastEntries = new ViewPastEntries(healthJournal);
            this.setVisible(false);
        }

        if (e.getActionCommand().equals(("Menu"))) {
            MenuPanel menuPanel = new MenuPanel(healthJournal);
            this.setVisible(false);
        }
    }
}
package ui;

import model.Entry;
import model.HealthJournal;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// User interface of a health journal
public class HealthJournalApp {
    private static final String JSON_STORE = "./data/healthjournal.json";
    private HealthJournal myHealthJournal;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs health journal application
    public HealthJournalApp() throws FileNotFoundException {
        myHealthJournal = new HealthJournal();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runHealthJournal();
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs
    public void runHealthJournal() {
        boolean keepGoing = true;

        while (keepGoing) {
            welcomeScreen();
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                welcomeScreenSelection(command);
            }
        }
        System.out.println("\nStay healthy & talk to you soon!");
    }

    // EFFECTS: displays option for user to open journal. add a new entry, or quit
    private void welcomeScreen() {
        System.out.println("\nMy Health Journal");
        System.out.println("\tview: View your entries");
        System.out.println("\tadd: Add a new entry");
        System.out.println("\tassess: Assess your health");
        System.out.println("\tsave: Save health journal to file");
        System.out.println("\tload: Load health journal from file");
        System.out.println("\tquit: Quit");
    }


    // REQUIRES: t, s, f, a, m >= 0
    // MODIFIES: this
    // EFFECTS: adds a new entry with the given fields to myHealthJournal
    protected void addNewEntry(Scanner input) {
        Entry entry = new Entry(0, 0, 0,0, 0);
        System.out.println("New Entry:");

        System.out.println("\nWhat's the numerical ID that you would like to assign to this entry?.");
        entry.setEntryID(input.nextInt());

        System.out.println("\nHow many hours of sleep did you get last night?");
        entry.setSleepAnswer(input.nextInt());

        System.out.println("\nHow many meals did you eat today?");
        entry.setFoodAnswer(input.nextInt());

        System.out.println("\nHow many minutes of physical activity did you engage in today?");
        entry.setActivityAnswer(input.nextInt());

        System.out.println("\nOn a scale of 1-5, how would you rate your overall mood today?");
        entry.setMoodAnswer(input.nextInt());

        System.out.println("Entry Saved!");
        myHealthJournal.addNewEntry(entry);
        myHealthJournal.addNewEntryID(entry);
    }

    // EFFECTS: print a list of the entry IDs of the entries in myHealthJournal
    protected void viewPastEntries() {
        System.out.println("\nMy Journal Entries");
        if (myHealthJournal.getJournalEntries().isEmpty()) {
            System.out.println("You have no existing entries - try adding one from the main menu");
        } else {
            for (Entry e : myHealthJournal.getJournalEntries()) {
                System.out.println(e.getEntryID());
            }
            System.out.println("Type the ID of the entry you'd like to view");
            selectEntry();
        }
    }

    // EFFECTS: displays a string summary of the entry with the given ID
    private void selectEntry() {
        int id;
        id = input.nextInt();
        if (myHealthJournal.containsEntry(id)) {
            Entry selectedEntry = myHealthJournal.getEntryFromID(id);
            String entrySummary = selectedEntry.displayEntry();
            System.out.print(entrySummary);
        } else {
            System.out.print("Couldn't find entry with the given ID - Please try again.");
            viewPastEntries();
        }
    }

    // EFFECTS: prints the selection menu for the assess health feature and interprets the user's selection
    protected void assessHealthSelection() {
        String command;
        if (myHealthJournal.getJournalEntries().size() < 3) {
            System.out.println("You need at least three entries in your journal to adequately assess your health.");
        } else {
            assessHealthOptionScreen();
            command = input.next();
            if (command.equals("sleep")) {
                interpretSleepAssessment();
            } else if (command.equals("food")) {
                interpretFoodAssessment();
            } else if (command.equals("activity")) {
                interpretActivityAssessment();
            } else if (command.equals("mood")) {
                interpretMoodAssessment();
            } else {
                System.out.println("Invalid selection");
            }
        }
    }

    // EFFECTS: selection menu for the assess health feature
    private void assessHealthOptionScreen() {
        System.out.println("Which area of your health would you like to assess?");
        System.out.println("\tsleep");
        System.out.println("\tfood");
        System.out.println("\tactivity");
        System.out.println("\tmood");
    }

    // EFFECTS: prints feedback for sleep based on assessSleep value
    private void interpretSleepAssessment() {
        if (myHealthJournal.assessSleep() == 3) {
            System.out.println("You've been getting an adequate amount of sleep. Great work!");
        } else if (myHealthJournal.assessSleep() == 2) {
            System.out.println("You've been getting an average amount of sleep. Make sure to stay well-rested!");
        } else if (myHealthJournal.assessSleep() == 1) {
            System.out.println("You haven't been getting much sleep lately. Make sure to give yourself a break!");
        } else {
            System.out.println("You urgently need more rest. Go to bed immediately!");
        }
    }

    // EFFECTS: prints feedback for eating habits based on assessFood value
    private void interpretFoodAssessment() {
        if (myHealthJournal.assessFood() == 3) {
            System.out.println("You've been consistent with eating three meals a day. Great work!");
        } else if (myHealthJournal.assessFood() == 2) {
            System.out.println("You miss a meal on occasion. Try to eat three everyday!");
        } else if (myHealthJournal.assessFood() == 1) {
            System.out.println("You've missed quite a few meals. Make sure to feed yourself!");
        } else {
            System.out.println("You urgently need more fuel. Go grab a snack!");
        }
    }

    // EFFECTS: prints feedback for physical activity based on assessActivity value
    private void interpretActivityAssessment() {
        if (myHealthJournal.assessActivity() == 3) {
            System.out.println("You've been getting an adequate amount of exercise. Great work!");
        } else if (myHealthJournal.assessActivity() == 2) {
            System.out.println("You are active on occasion. Try to get at least 60 minutes per day!");
        } else if (myHealthJournal.assessActivity() == 1) {
            System.out.println("You haven't been active recently. Make sure to move around!");
        } else {
            System.out.println("You urgently need more physical activity. Go for a walk!");
        }
    }

    // EFFECTS: prints feedback for mood based on assessMood value
    private void interpretMoodAssessment() {
        if (myHealthJournal.assessMood() == 3) {
            System.out.println("You've been in a great mood recently! We love to see that!");
        } else if (myHealthJournal.assessMood() == 2) {
            System.out.println("Your mood has been fluctuating recently. We hope that it stays high moving forward!");
        } else if (myHealthJournal.assessMood() == 1) {
            System.out.println("Your mood has been low recently. Try to take it easy in the coming days!");
        } else {
            System.out.println("We're sad to see that your mood has been low. Seek help if necessary.");
        }
    }

    // MODIFIES: this
    // EFFECTS: interprets the command of user at welcome screen
    private void welcomeScreenSelection(String command) {
        if (command.equals("view")) {
            viewPastEntries();
        } else if (command.equals("add")) {
            addNewEntry(input);
        } else if (command.equals("assess")) {
            assessHealthSelection();
        } else if (command.equals("save")) {
            saveHealthJournal();
        } else if (command.equals("load")) {
            loadHealthJournal();
        } else {
            System.out.print("Invalid selection");
        }
    }

    protected HealthJournal getMyHealthJournal() {
        return myHealthJournal;
    }

    // EFFECTS: saves the health journal to file
    protected void saveHealthJournal() {
        try {
            jsonWriter.open();
            jsonWriter.write(myHealthJournal);
            jsonWriter.close();
            System.out.println("Saved your Health Journal to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads health journal from file
    protected void loadHealthJournal() {
        try {
            myHealthJournal = jsonReader.read();
            System.out.println("Loaded your Health Journal from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

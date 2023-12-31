package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents one entry in the health journal
public class Entry implements Writable {
    private int entryID;
    private int sleepAnswer;
    private int foodAnswer;
    private int activityAnswer;
    private int moodAnswer;

    // Constructs an entry with an ID, and answers to the entry questions
    public Entry(int t, int s, int f, int a, int m) {
        entryID = t;
        sleepAnswer = s;
        foodAnswer = f;
        activityAnswer = a;
        moodAnswer = m;
    }

    //EFFECTS: sets entry ID to the given integer
    public void setEntryID(int t) {
        entryID = t;
    }

    //EFFECTS: Sets answer to "How many hours of sleep did you get last night?"
    public void setSleepAnswer(int s) {
        sleepAnswer = s;
    }

    //EFFECTS: Sets answer to "How many meals did you eat today?"
    public void setFoodAnswer(int f) {
        foodAnswer = f;
    }

    //EFFECTS: Sets answer to "How many minutes of physical activity did you engage in today?"
    public void setActivityAnswer(int a) {
        activityAnswer = a;
    }

    //REQUIRES: 1 <= m <= 5
    //EFFECTS: Sets answer to "On a scale of 1 to 5, how would you rate your overall mood today?"
    public void setMoodAnswer(int m) {
        moodAnswer = m;
    }

    //EFFECTS: returns entry ID
    public Integer getEntryID() {
        return entryID;
    }

    //EFFECTS: returns sleepAnswer
    public int getSleepAnswer() {
        return sleepAnswer;
    }

    //EFFECTS: returns foodAnswer
    public int getFoodAnswer() {
        return foodAnswer;
    }

    //EFFECTS: returns activityAnswer
    public int getActivityAnswer() {
        return activityAnswer;
    }

    //EFFECTS: returns moodAnswer
    public int getMoodAnswer() {
        return moodAnswer;
    }

    // EFFECTS: returns a summary of the entry answers in the form of a string
    public String displayEntry() {
        return "Entry ID: " + entryID + "\n"
                + "Hours of Sleep: " + sleepAnswer + "\n"
                + "What I Ate: " + foodAnswer + "\n"
                + "Minutes of Physical Activity: " + activityAnswer + "\n"
                + "Overall Mood Rated From 1-5: " + moodAnswer + "\n";
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonEntry = new JSONObject();
        jsonEntry.put("entryID", entryID);
        jsonEntry.put("sleep", sleepAnswer);
        jsonEntry.put("food", foodAnswer);
        jsonEntry.put("activity", activityAnswer);
        jsonEntry.put("mood", moodAnswer);
        return jsonEntry;
    }
}

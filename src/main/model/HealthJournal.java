package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a journal with entries
public class HealthJournal implements Writable {

    private final ArrayList<Entry> journal;
    public final ArrayList<Integer> entryIDs;

    // EFFECTS: constructs an empty journal
    public HealthJournal() {
        journal = new ArrayList<>();
        entryIDs = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("New health journal created."));
    }

    // MODIFIES: this
    // EFFECTS: adds entry to the journal & entry ID to the list of journal entries
    public void addNewEntry(Entry e) {
        journal.add(e);
        EventLog.getInstance().logEvent(new Event("Entry " + e.getEntryID() + " added to journal."));
    }

    // MODIFIES: this
    // EFFECTS: adds entryID to the list of journal entries
    public void addNewEntryID(Entry e) {
        entryIDs.add(e.getEntryID());
    }

    // REQUIRES: journal.contains(e)
    // MODIFIES: this
    // EFFECTS: adds entryID to the list of journal entries
    public void removeEntry(Entry e) {
        journal.remove(e);
        entryIDs.remove(e.getEntryID());
        EventLog.getInstance().logEvent(new Event("Entry " + e.getEntryID() + " removed from journal."));
    }


    //EFFECTS: returns list of entries saved to the journal
    public List<Entry> getJournalEntries() {
        return journal;
    }

    //EFFECTS: returns list of titles of the entries saved to the journal
    public List<Integer> getJournalEntryIDs() {
        return entryIDs;
    }

    // REQUIRES: id >=0
    // EFFECTS: returns true if an entry with the given ID is in the journal
    public boolean containsEntry(Integer id) {
        return entryIDs.contains(id);
    }

    // REQUIRES: only called when containsEntry(id) is true
    // EFFECTS: returns the entry in the journal with the given entry ID
    public Entry getEntryFromID(int id) {
        Entry validEntry = new Entry(0, 0, 0, 0,0);
        for (Entry e : journal) {
            if (e.getEntryID() == id) {
                validEntry = e;
            }
        }
        EventLog.getInstance().logEvent(new Event("Viewed entry " + validEntry.getEntryID() + " in detail."));
        return validEntry;
    }

    // REQUIRES: journal.size() >= 3
    // MODIFIES: sleepRating
    // EFFECTS: gets answers to the sleep question from the last 3 entries added to the journal,
    // adds 1 to sleepRating if entry records that user got > 6 hours of sleep
    public Integer assessSleep() {
        int lastEntryPos = journal.size() - 1;
        int secLastEntryPos = lastEntryPos - 1;
        int thirdLastEntryPos = secLastEntryPos - 1;
        int sleepRating = 0;

        if (journal.get(lastEntryPos).getSleepAnswer() > 6) {
            sleepRating = sleepRating + 1;
        }

        if (journal.get(secLastEntryPos).getSleepAnswer() > 6) {
            sleepRating = sleepRating + 1;
        }

        if (journal.get(thirdLastEntryPos).getSleepAnswer() > 6) {
            sleepRating = sleepRating + 1;
        }

        return sleepRating;
    }

    // REQUIRES: journal.size() >= 3
    // MODIFIES: foodRating
    // EFFECTS: gets answers to the food question from the last 3 entries added to the journal,
    // adds 1 to foodRating if entry records that >= 3 meals were eaten
    public Integer assessFood() {
        int lastEntryPos = journal.size() - 1;
        int secLastEntryPos = lastEntryPos - 1;
        int thirdLastEntryPos = secLastEntryPos - 1;
        int foodRating = 0;

        if (journal.get(lastEntryPos).getFoodAnswer() >= 3) {
            foodRating = foodRating + 1;
        }

        if (journal.get(secLastEntryPos).getFoodAnswer() >= 3) {
            foodRating = foodRating + 1;
        }

        if (journal.get(thirdLastEntryPos).getFoodAnswer() >= 3) {
            foodRating = foodRating + 1;
        }

        return foodRating;
    }

    // REQUIRES: journal.size() >= 3
    // MODIFIES: activityRating
    // EFFECTS: gets answers to the activity question from the last 3 entries added to the journal,
    // adds 1 to foodRating if entry records that user got >= 30 minutes of physical activity
    public Integer assessActivity() {
        int lastEntryPos = journal.size() - 1;
        int secLastEntryPos = lastEntryPos - 1;
        int thirdLastEntryPos = secLastEntryPos - 1;
        int activityRating = 0;

        if (journal.get(lastEntryPos).getActivityAnswer() >= 60) {
            activityRating = activityRating + 1;
        }

        if (journal.get(secLastEntryPos).getActivityAnswer() >= 60) {
            activityRating = activityRating + 1;
        }

        if (journal.get(thirdLastEntryPos).getActivityAnswer() >= 60) {
            activityRating = activityRating + 1;
        }

        return activityRating;
    }

    // REQUIRES: journal.size() >= 3
    // MODIFIES: moodRating
    // EFFECTS: gets answers to the mood question from the last 3 entries added to the journal,
    // adds 1 to foodRating if entry records that user rated mood > 2
    public Integer assessMood() {
        int lastEntryPos = journal.size() - 1;
        int secLastEntryPos = lastEntryPos - 1;
        int thirdLastEntryPos = secLastEntryPos - 1;
        int moodRating = 0;

        if (journal.get(lastEntryPos).getMoodAnswer() > 2) {
            moodRating = moodRating + 1;
        }

        if (journal.get(secLastEntryPos).getMoodAnswer() > 2) {
            moodRating = moodRating + 1;
        }

        if (journal.get(thirdLastEntryPos).getMoodAnswer() > 2) {
            moodRating = moodRating + 1;
        }

        return moodRating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject journalJson = new JSONObject();
        journalJson.put("entries", entriesToJson());
        journalJson.put("entryIDs", entryIDs);
        return journalJson;
    }

    // EFFECTS: returns entries in this health journal as a JSON array
    private JSONArray entriesToJson() {
        JSONArray entriesJsonArray = new JSONArray();

        for (Entry e : journal) {
            entriesJsonArray.put(e.toJson());
        }

        return entriesJsonArray;
    }
}


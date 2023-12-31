package persistence;

import model.Entry;
import model.HealthJournal;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads health journal from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads health journal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public HealthJournal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHealthJournal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses health journal from JSON object and returns it
    private HealthJournal parseHealthJournal(JSONObject jsonObject) {
        HealthJournal healthJournal = new HealthJournal();
        addEntries(healthJournal, jsonObject);
        addEntryIDs(healthJournal, jsonObject);
        return healthJournal;
    }

    // MODIFIES: healthJournal
    // EFFECTS: parses entries from JSON object and adds them to health journal
    private void addEntries(HealthJournal healthJournal, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addNewEntry(healthJournal, nextEntry);
        }
    }

    // MODIFIES: healthJournal
    // EFFECTS: parses entryIDs from JSON object and adds them to health journal
    private void addEntryIDs(HealthJournal healthJournal, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entryIDs");
        for (Object json : jsonArray) {
            if (json instanceof Integer) {
                Integer nextEntryID = (Integer) json;
                healthJournal.entryIDs.add(nextEntryID);
            }
        }
    }

    // MODIFIES: healthJournal
    // EFFECTS: parses thingy from JSON object and adds it to healthJournal
    private void addNewEntry(HealthJournal healthJournal, JSONObject jsonObject) {
        int entryID = jsonObject.getInt("entryID");
        int sleepAnswer = jsonObject.getInt("sleep");
        int foodAnswer = jsonObject.getInt("food");
        int activityAnswer = jsonObject.getInt("activity");
        int moodAnswer = jsonObject.getInt("mood");
        Entry entry = new Entry(entryID,sleepAnswer,foodAnswer,activityAnswer,moodAnswer);
        healthJournal.addNewEntry(entry);
    }
}
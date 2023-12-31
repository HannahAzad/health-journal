package persistence;

import model.Entry;
import model.HealthJournal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            HealthJournal healthJournal = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyHealthJournal() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHealthJournal.json");
        try {
            HealthJournal healthJournal = reader.read();
            List<Entry> entries = healthJournal.getJournalEntries();
            assertEquals(0, entries.size());
            List<Integer> entryIDs = healthJournal.getJournalEntryIDs();
            assertEquals(0, entryIDs.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHealthJournal() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHealthJournal.json");
        try {
            HealthJournal healthJournal = reader.read();
            List<Entry> entries = healthJournal.getJournalEntries();
            assertEquals(2, entries.size());
            checkEntry(1,1,1,1,1, entries.get(0));
            checkEntry(2,2,2,2,2, entries.get(1));
            List<Integer> entryIDs = healthJournal.getJournalEntryIDs();
            assertEquals(2, entryIDs.size());
            assertEquals(1, entryIDs.get(0));
            assertEquals(2, entryIDs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

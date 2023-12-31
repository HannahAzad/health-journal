package persistence;

import model.Entry;
import model.HealthJournal;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            HealthJournal healthJournal = new HealthJournal();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyHealthJournal() {
        try {
            HealthJournal healthJournal = new HealthJournal();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHealthJournal.json");
            writer.open();
            writer.write(healthJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHealthJournal.json");
            healthJournal = reader.read();
            List<Entry> entries = healthJournal.getJournalEntries();
            assertEquals(0, entries.size());
            List<Integer> entryIDs = healthJournal.getJournalEntryIDs();
            assertEquals(0, entryIDs.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHealthJournal() {
        try {
            HealthJournal healthJournal = new HealthJournal();
            healthJournal.addNewEntry(new Entry(1, 1,1,1,1));
            healthJournal.addNewEntryID(new Entry(1, 1,1,1,1));
            healthJournal.addNewEntry(new Entry(2,2,2,2,2));
            healthJournal.addNewEntryID(new Entry(2,2,2,2,2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHealthJournal.json");
            writer.open();
            writer.write(healthJournal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHealthJournal.json");
            healthJournal = reader.read();
            List<Entry> entries = healthJournal.getJournalEntries();
            assertEquals(2, entries.size());
            checkEntry(1,1,1,1,1, entries.get(0));
            checkEntry(2,2,2,2,2, entries.get(1));
            List<Integer> entryIDs = healthJournal.getJournalEntryIDs();
            assertEquals(2, entryIDs.size());
            assertEquals(1, entryIDs.get(0));
            assertEquals(2, entryIDs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
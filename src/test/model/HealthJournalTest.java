package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HealthJournalTest {
    HealthJournal healthJournalTest;
    Entry entry1;
    Entry entry2;
    Entry entry3;
    Entry high;
    Entry low;

    @BeforeEach
    void runBefore() {
        healthJournalTest = new HealthJournal();
        entry1 = new Entry(1, 0, 0, 0, 0);
        entry2 = new Entry(2, 8, 3, 60, 5);
        entry3 = new Entry(3, 3, 1, 10, 1);

        high = new Entry(4, 8, 3,60,5);
        low = new Entry (5, 1,1,1,1);
    }

    @Test
    void testConstructor() {
        List<Entry> journal = healthJournalTest.getJournalEntries();
        assertEquals(0, journal.size());
    }

    @Test
    void testAddNewEntry() {
        List<Entry> journal = healthJournalTest.getJournalEntries();
        List<Integer> entryIDs = healthJournalTest.getJournalEntryIDs();
        healthJournalTest.addNewEntry(entry1);
        healthJournalTest.addNewEntryID(entry1);

        assertEquals(1, journal.size());
        assertEquals(entry1, journal.get(0));
        assertEquals(1, entryIDs.size());
        assertEquals(1, entryIDs.get(0));

    }

    @Test
    void testAddMultipleEntries() {
        List<Entry> journal = healthJournalTest.getJournalEntries();
        List<Integer> entryIDs = healthJournalTest.getJournalEntryIDs();
        healthJournalTest.addNewEntry(entry1);
        healthJournalTest.addNewEntryID(entry1);
        healthJournalTest.addNewEntry(entry2);
        healthJournalTest.addNewEntryID(entry2);
        healthJournalTest.addNewEntry(entry3);
        healthJournalTest.addNewEntryID(entry3);

        assertEquals(3, journal.size());
        assertEquals(entry1, journal.get(0));
        assertEquals(entry2, journal.get(1));
        assertEquals(entry3, journal.get(2));

        assertEquals(3, entryIDs.size());
        assertEquals(1, entryIDs.get(0));
        assertEquals(2, entryIDs.get(1));
        assertEquals(3, entryIDs.get(2));

    }

    @Test
    void testRemoveEntry() {
        healthJournalTest.addNewEntry(entry1);
        healthJournalTest.addNewEntryID(entry1);
        healthJournalTest.addNewEntry(entry3);
        healthJournalTest.addNewEntryID(entry3);
        healthJournalTest.removeEntry(entry1);

        assertFalse(healthJournalTest.containsEntry(1));
        assertTrue(healthJournalTest.containsEntry(3));
    }

    @Test
    void testContainsEntry() {
        healthJournalTest.addNewEntry(entry1);
        healthJournalTest.addNewEntryID(entry1);
        healthJournalTest.addNewEntry(entry3);
        healthJournalTest.addNewEntryID(entry3);

        assertFalse(healthJournalTest.containsEntry(0));
        assertTrue(healthJournalTest.containsEntry(1));
        assertFalse(healthJournalTest.containsEntry(2));
        assertTrue(healthJournalTest.containsEntry(3));

        healthJournalTest.addNewEntry(new Entry(1234567890, 1,1,1,1));
        healthJournalTest.addNewEntryID(new Entry(1234567890, 1,1,1,1));
        assertTrue(healthJournalTest.containsEntry(1234567890));
        assertFalse(healthJournalTest.containsEntry(123456789));
    }

    @Test
    void testGetEntryFromID() {
        healthJournalTest.addNewEntry(entry1);
        healthJournalTest.addNewEntryID(entry1);
        healthJournalTest.addNewEntry(entry2);
        healthJournalTest.addNewEntryID(entry2);
        healthJournalTest.addNewEntry(entry3);
        healthJournalTest.addNewEntryID(entry3);

        assertEquals(entry1, healthJournalTest.getEntryFromID(1));
        assertEquals(entry2, healthJournalTest.getEntryFromID(2));
        assertEquals(entry3, healthJournalTest.getEntryFromID(3));
    }

    @Test
    void testAssessSleep() {
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);

        assertEquals(3, healthJournalTest.assessSleep());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(2, healthJournalTest.assessSleep());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(1, healthJournalTest.assessSleep());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(0, healthJournalTest.assessSleep());

        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        assertEquals(1, healthJournalTest.assessSleep());
    }

    @Test
    void testAssessFood() {
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);

        assertEquals(3, healthJournalTest.assessFood());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(2, healthJournalTest.assessFood());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(1, healthJournalTest.assessFood());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(0, healthJournalTest.assessFood());

        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        assertEquals(1, healthJournalTest.assessFood());

    }

    @Test
    void testAssessActivity() {
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);

        assertEquals(3, healthJournalTest.assessActivity());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(2, healthJournalTest.assessActivity());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(1, healthJournalTest.assessActivity());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(0, healthJournalTest.assessActivity());

        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        assertEquals(1, healthJournalTest.assessActivity());

    }

    @Test
    void testAssessMood() {
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);

        assertEquals(3, healthJournalTest.assessMood());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(2, healthJournalTest.assessMood());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(1, healthJournalTest.assessMood());

        healthJournalTest.addNewEntry(low);
        healthJournalTest.addNewEntryID(low);
        assertEquals(0, healthJournalTest.assessMood());

        healthJournalTest.addNewEntry(high);
        healthJournalTest.addNewEntryID(high);
        assertEquals(1, healthJournalTest.assessMood());

    }

}
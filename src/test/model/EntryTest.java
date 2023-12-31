package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    private Entry eTest;

    @BeforeEach
    void runBefore() {
        eTest = new Entry(0,0,0,0,0);
    }

    @Test
    void testConstructor() {
        assertEquals(0, eTest.getEntryID());
        assertEquals(0, eTest.getSleepAnswer());
        assertEquals(0, eTest.getFoodAnswer());
        assertEquals(0, eTest.getActivityAnswer());
        assertEquals(0, eTest.getMoodAnswer());

        eTest.setEntryID(1);
        assertEquals(1, eTest.getEntryID());

        eTest.setSleepAnswer(8);
        assertEquals(8, eTest.getSleepAnswer());

        eTest.setFoodAnswer(3);
        assertEquals(3, eTest.getFoodAnswer());

        eTest.setActivityAnswer(30);
        assertEquals(30, eTest.getActivityAnswer());

        eTest.setMoodAnswer(5);
        assertEquals(5, eTest.getMoodAnswer());
    }

    @Test
    void testDisplayEntry() {
        eTest.setEntryID(1);
        eTest.setSleepAnswer(8);
        eTest.setFoodAnswer(3);
        eTest.setActivityAnswer(30);
        eTest.setMoodAnswer(5);

        assertEquals("Entry ID: " + 1 + "\n"
                + "Hours of Sleep: " + 8 + "\n"
                + "What I Ate: " + 3 + "\n"
                + "Minutes of Physical Activity: " + 30 + "\n"
                + "Overall Mood Rated From 1-5: " + 5 + "\n",
                eTest.displayEntry());
    }

}

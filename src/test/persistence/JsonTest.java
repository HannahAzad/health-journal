package persistence;

import model.Entry;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void
    checkEntry(Integer entryID,
               Integer sleep,
               Integer food,
               Integer activity,
               Integer mood,
               Entry entry) {
        assertEquals(entryID, entry.getEntryID());
        assertEquals(sleep, entry.getSleepAnswer());
        assertEquals(food, entry.getFoodAnswer());
        assertEquals(activity, entry.getActivityAnswer());
        assertEquals(mood, entry.getMoodAnswer());
    }
}
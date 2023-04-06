package events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log of student events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog for the student and that the student has global access
 * to the single instance of the EventLog.
 */
public class EventLog implements Iterable<Event> {
    /**
     * the only EventLog in the system (Singleton Design Pattern)
     */
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    //EFFECTS: creates an event log with an empty list of events
    private EventLog() {
        events = new ArrayList<Event>();
    }

    /**
     * EFFECTS: Gets instance of EventLog - creates it
     * if it doesn't already exist.
     * (Singleton Design Pattern)
     *
     * @return instance of EventLog
     */
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    /**
     * MODIFIES: this
     * EFFECTS: Adds an event to the event log.
     *
     * @param e the event to be added
     */
    //
    public void logEvent(Event e) {
        events.add(e);
    }

    /**
     * MODIFIES: this
     * EFFECTS: Clears the event log and logs the event.
     */
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    //EFFECTS: returns the iterator for events
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}

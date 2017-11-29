package pl.pollub.cs.pentagoncafe.flare.domain;

import javax.persistence.Entity;
import java.util.List;

public class User {

    private List<Event> eventsList;

    public List<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }
}

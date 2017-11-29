package pl.pollub.cs.pentagoncafe.flare.domain;

import javax.persistence.Entity;
import java.util.List;

public class User {

    private List<Event> eventsList;
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }

    public void setEventsList(List<Event> eventsList) {
        this.eventsList = eventsList;
    }
}

package pl.pollub.cs.pentagoncafe.flare.domain;

import java.util.List;

public class Event {

    private String id;

    private List<User> entrantsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getEntrantsList() {
        return entrantsList;
    }

    public void setEntrantsList(List<User> entrantsList) {
        this.entrantsList = entrantsList;
    }
}

package pl.pollub.cs.pentagoncafe.flare.domain;

import javax.persistence.Entity;
import java.util.List;

public class Event {

    List<User> entrantsList;

    public List<User> getEntrantsList() {
        return entrantsList;
    }

    public void setEntrantsList(List<User> entrantsList) {
        this.entrantsList = entrantsList;
    }
}

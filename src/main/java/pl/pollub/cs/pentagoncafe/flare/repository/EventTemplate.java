package pl.pollub.cs.pentagoncafe.flare.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Term;
import org.springframework.data.rest.core.config.Projection;
import pl.pollub.cs.pentagoncafe.flare.domain.*;

import java.util.Date;
import java.util.List;

@Projection(name = "event", types = { Event.class })
public interface EventTemplate {
    ObjectId getId();

    User getOrganizer();

    List<Link> getLinks();

    Address getAddress();

    List<Term> getEventStatistic();

    List<Vote> getVotes();

    String getTitle();

    String getDescription();

    int getDuration();

    Date getDateOfEventApproval();

    boolean isApproved();

    boolean isOnlyForRegisteredUsers();

    String getImageURL();
}

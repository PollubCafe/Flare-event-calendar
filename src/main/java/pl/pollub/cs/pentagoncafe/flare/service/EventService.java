package pl.pollub.cs.pentagoncafe.flare.service;

import org.springframework.data.domain.Page;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

import java.util.List;

public interface EventService {

    Event save(Event event);

    List<Event> getEventsList();

    Event find(String id);

    Event applyParticipation(String id, User user);

    Page<Event> getPageOfEventsByPageNumber(int pageNumber);
}

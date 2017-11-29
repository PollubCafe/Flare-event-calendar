package pl.pollub.cs.pentagoncafe.flare.service;

import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public Event save(Event event) {
        return null;
    }

    @Override
    public List<Event> getEventsList() {
        return null;
    }

    @Override
    public Event find(String id) {
        return null;
    }

    @Override
    public Event applyParticipation(String id, User user) {
        return null;
    }
}

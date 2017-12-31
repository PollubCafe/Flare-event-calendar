package pl.pollub.cs.pentagoncafe.flare.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.service.EventService;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final int DEFAULT_PAGE_SIZE = 7;

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

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

    @Override
    public Page<Event> getPageOfEventsByPageNumber(int pageNumber) {
        return eventRepository.findAll(new PageRequest(pageNumber,DEFAULT_PAGE_SIZE));
    }
}

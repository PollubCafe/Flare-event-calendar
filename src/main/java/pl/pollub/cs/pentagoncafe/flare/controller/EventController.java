package pl.pollub.cs.pentagoncafe.flare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Stats;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.service.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);
    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/")
    public Event saveEvent(@RequestBody Event event) {
        return eventService.save(event);
    }

    @GetMapping("/")
    public List<Event> getEvents() {
        return eventService.getEventsList();
    }

    @PostMapping("/{id}/participation")
    public Event applyParticipation(@PathVariable String id, @RequestBody User user) {

        Event event = eventService.find(id);
        event.getEntrantsList().add(user);

        return event;
    }

    @GetMapping("{id}/stats")
    public Stats getStats(@PathVariable String id) {
        Event event = eventService.find(id);

        return new Stats(event).getStatistics();
    }

}

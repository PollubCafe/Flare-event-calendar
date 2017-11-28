package pl.pollub.cs.pentagoncafe.flare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);
    private EventService eventService;

    @Autowired
    public EventController(eventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/")
    public List<Event> saveEvent() {
        eventService.save();
    }

    @GetMapping("/")
    public List<Event> getEvents() {
        return eventService.getEventsList();
    }

    @PostMapping("/{id}/participation")
    public User applyParticipation(@PathVariable String id, @RequestBody User user) {
        return eventService.getEventsList().getEvent(id).getEntrantsList().add(user);
    }

    @GetMapping("{id}/stats")
    public Stats getStats(@PathVariable String id) {
        return eventService.getEvetsList().getEvent(id).getStats();
    }

}

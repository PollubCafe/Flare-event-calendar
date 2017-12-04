package pl.pollub.cs.pentagoncafe.flare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Stats;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.service.EventService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @PostMapping("/")
    public ResponseEntity<Event> saveEvent(@RequestBody Event event, UriComponentsBuilder ucb) {
        Event event1 = eventService.save(event);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri =
                ucb.path("/api/events/")
                        .path(String.valueOf(event1.getId()))
                        .build()
                        .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(event1, headers, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventService.getEventsList();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/{id}/participation")
    public ResponseEntity<Event> applyParticipation(@PathVariable String id, @RequestBody User user) {
        Event event = eventService.applyParticipation(id, user);

        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @GetMapping("/{id}/stats")
    public ResponseEntity<Stats> getStats(@PathVariable String id) {
        Event event = eventService.find(id);
        Stats stats = new Stats(event).getStatistics();

        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}

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
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user, UriComponentsBuilder ucb) {
        User user1 = userService.save(user);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri =
                ucb.path("/api/users/")
                        .path(String.valueOf(user1.getId()))
                        .build()
                        .toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(user1, headers, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable String id) {
        List<Event> usersEvents = userService.getUserEventsList(id);

        return new ResponseEntity<>(usersEvents, HttpStatus.OK);
    }

}

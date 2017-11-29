package pl.pollub.cs.pentagoncafe.flare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }


    @GetMapping("/{id}/events")
    public List<Event> getUserEvents(@PathVariable String id) {
        User user = userService.find(id);
        return user.getEventsList();
    }

}

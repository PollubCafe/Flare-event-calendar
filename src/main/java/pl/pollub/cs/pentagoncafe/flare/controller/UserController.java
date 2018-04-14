package pl.pollub.cs.pentagoncafe.flare.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventService;
import pl.pollub.cs.pentagoncafe.flare.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") ObjectId id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping(value = "/nick/{nick}")
    public ResponseEntity<UserResponseDTO> getUserByNick(@PathVariable("nick") String nick) {
        return new ResponseEntity<>(userService.getUserByNick(nick), HttpStatus.OK);
    }


    @GetMapping(value = "/event/{id}")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersAssignedToEvent(@PathVariable("id") ObjectId id) {
        return new ResponseEntity<>(userService.getAllUsersAssignedToEvent(id), HttpStatus.OK);
    }


    @GetMapping(value = "/admins")
    public ResponseEntity<List<UserResponseDTO>> getAllAdmins() {
        return new ResponseEntity<>(userService.getAllAdmins(), HttpStatus.OK);
    }

    @GetMapping(value = "/unBanned")
    public ResponseEntity<List<UserResponseDTO>> getAllUnBannedUsers() {
        return new ResponseEntity<>(userService.getAllUnBannedUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}


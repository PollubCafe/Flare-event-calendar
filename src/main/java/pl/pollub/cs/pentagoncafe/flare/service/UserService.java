package pl.pollub.cs.pentagoncafe.flare.service;


import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User find(String id);

    List<Event> getUserEventsList(String id);
}

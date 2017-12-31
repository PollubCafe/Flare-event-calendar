package pl.pollub.cs.pentagoncafe.flare.service.implementation;

import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User find(String id) {
        return null;
    }

    @Override
    public List<Event> getUserEventsList(String id) {
        return null;
    }
}

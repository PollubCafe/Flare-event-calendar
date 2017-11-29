package pl.pollub.cs.pentagoncafe.flare.service;


import pl.pollub.cs.pentagoncafe.flare.domain.User;

public interface UserService {
    User save(User user);

    User find(String id);
}

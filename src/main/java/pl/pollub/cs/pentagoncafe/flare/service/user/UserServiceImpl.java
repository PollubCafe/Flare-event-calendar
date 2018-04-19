package pl.pollub.cs.pentagoncafe.flare.service.user;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.mapper.UserMapperImpl;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EventRepository eventRepository;
    private UserMapperImpl userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapperImpl userMapper, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.eventRepository = eventRepository;
    }

    @Override
    public UserResponseDTO getUser(ObjectId id) {

        User foundUser = userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(User.class));

        return userMapper.mapToResponseDTO(foundUser);
    }

    @Override
    public UserResponseDTO getUserByNick(String nick) {
        User foundUser = userRepository.findByNick(nick)
                .orElseThrow(() -> new ObjectNotFoundException(User.class, "nick", nick));

        return userMapper.mapToResponseDTO(foundUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsersAssignedToEvent(ObjectId EventId) {
        Event event = eventRepository.findById(EventId)
                .orElseThrow(() -> new ObjectNotFoundException(Event.class));

        return event.getParticipation().stream()
                .map(Participation::getUser)
                .map(userMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getAllAdmins() {
        List<User> allUsers = userRepository.findAllByRoleIs(Role.ADMIN);

        return allUsers.stream()
                .map(userMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();

        return allUsers.stream()
                .map(userMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponseDTO> getAllUnBannedUsers() {
        List<User> foundUsers = userRepository.findAllByBannedIsFalse();

        return foundUsers.stream()
                .map(userMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }
}

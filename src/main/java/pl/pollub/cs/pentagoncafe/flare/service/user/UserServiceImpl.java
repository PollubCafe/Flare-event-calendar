package pl.pollub.cs.pentagoncafe.flare.service.user;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.mapper.UserMapper;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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
}

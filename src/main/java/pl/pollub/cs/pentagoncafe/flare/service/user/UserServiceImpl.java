package pl.pollub.cs.pentagoncafe.flare.service.user;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserResponseDTO getUser(ObjectId id) {
        return null;
    }

    @Override
    public UserResponseDTO getUserByName(String name) {
        return null;
    }
}

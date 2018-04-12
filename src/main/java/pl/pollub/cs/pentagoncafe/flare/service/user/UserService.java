package pl.pollub.cs.pentagoncafe.flare.service.user;

import org.bson.types.ObjectId;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

public interface UserService {
    UserResponseDTO getUser(ObjectId id);
    UserResponseDTO getUserByNick(String nick);
}

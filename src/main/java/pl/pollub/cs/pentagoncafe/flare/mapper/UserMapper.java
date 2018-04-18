package pl.pollub.cs.pentagoncafe.flare.mapper;

import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

public interface UserMapper {
    public UserResponseDTO mapToResponseDTO(User user);
}

package pl.pollub.cs.pentagoncafe.flare.mapper;

import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserResponseDTO mapToResponseDTO(User user){
        return UserResponseDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .nick(user.getNick())
                .role(user.getRole().name())
                .build();
    }

}

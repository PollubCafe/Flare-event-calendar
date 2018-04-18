package pl.pollub.cs.pentagoncafe.flare.testObjectFactories;

import pl.pollub.cs.pentagoncafe.flare.DTO.response.AuthUserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.UserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.ActivationToken;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;

public class TestUserFactory {

    public static User createTestUser(){
        return User.builder()
                .name("Jan")
                .surname("Kowalski")
                .email("jan.kowalski@gmail.com")
                .phoneNumber("2346453342")
                .nick("yanko")
                .role(Role.USER)
                .enabled(true)
                .banned(false)
                .activationToken(new ActivationToken("fdakslgsrec"))
                .build();
    }

    public static UserResponseDTO createTestUserResponseDTO(){
        return UserResponseDTO.builder()
                .name("Jan")
                .surname("Kowalski")
                .email("jan.kowalski@gmail.com")
                .phoneNumber("2346453342")
                .nick("yanko")
                .role(Role.USER.toString())
                .build();
    }
}

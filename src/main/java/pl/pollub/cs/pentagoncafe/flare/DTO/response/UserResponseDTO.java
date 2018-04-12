package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;

@Getter
@Setter
@Builder
public class UserResponseDTO {

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String role;
    private String nick;

}

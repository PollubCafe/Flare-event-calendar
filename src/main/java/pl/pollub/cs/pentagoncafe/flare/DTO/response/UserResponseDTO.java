package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.ServerProperties;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private String username;
    private String email;
    private String role;
}

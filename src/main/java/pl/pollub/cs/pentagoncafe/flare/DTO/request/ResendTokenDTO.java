package pl.pollub.cs.pentagoncafe.flare.DTO.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;

@Data
public class ResendTokenDTO {
    @NonNull
    @Pattern(regexp = "(^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.+[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@+(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$)",
            message = "validation.registration.email.pattern")
    String email;
}

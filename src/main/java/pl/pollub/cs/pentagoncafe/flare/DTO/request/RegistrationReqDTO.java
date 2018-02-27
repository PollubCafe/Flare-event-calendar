package pl.pollub.cs.pentagoncafe.flare.DTO.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class RegistrationReqDTO {
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻa-zzżźćńółęąś0-9]{3,30}$", message = "validation.registration.nick.pattern")
    private String nick;
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-zzżźćńółęąś]{2,19}$", message = "validation.registration.surname.pattern")
    private String surname;
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-zzżźćńółęąś]{2,19}$", message = "validation.registration.name.pattern")
    private String name;
    @NotBlank
    @Pattern(regexp = "(^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.+[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@+(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$)",
            message = "validation.registration.email.pattern")
    private String email;
    @NotNull
    @Pattern(regexp = "^$|^[0-9]{9,11}$", message = "validation.registration.phoneNumber.pattern")
    private String phoneNumber;
    @NotBlank
    @Size(min = 5, message = "validation.registration.password.pattern")
    private String password;
}

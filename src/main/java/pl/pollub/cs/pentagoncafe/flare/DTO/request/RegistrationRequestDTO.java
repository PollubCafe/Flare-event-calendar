package pl.pollub.cs.pentagoncafe.flare.DTO.request;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequestDTO {
    @NonNull
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻa-zzżźćńółęąś0-9]{3,30}$", message = "Nick must has between 3 to 30 letters or digits")
    private String nick;
    @NonNull
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-zzżźćńółęąś]{2,19}$", message = "Surname must start with big letter and have between 3 to 20 chars")
    private String surname;
    @NonNull
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-zzżźćńółęąś]{2,19}$", message = "Name must start with big letter and have between 3 to 20 chars")
    private String name;
    @NonNull
    @Pattern(regexp = "(^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.+[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@+(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$)",
            message = "Invalid email format")
    private String email;
    @NonNull
    @Pattern(regexp = "^$|^[0-9]{9,11}$", message = "Invalid phone number format")
    private String phoneNumber;
    @NonNull
    @Size(min = 5, message = "Password must have more than 5 characters")
    private String password;
}

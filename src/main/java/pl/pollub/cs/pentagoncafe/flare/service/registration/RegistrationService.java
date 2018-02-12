package pl.pollub.cs.pentagoncafe.flare.service.registration;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.ResendTokenDTO;

public interface RegistrationService {
    void register(RegistrationRequestDTO registrationRequestDTO);
    void resendToken(ResendTokenDTO resendTokenDTO);
    void finishRegistration(String token);
}

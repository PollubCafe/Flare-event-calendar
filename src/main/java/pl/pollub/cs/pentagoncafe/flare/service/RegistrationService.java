package pl.pollub.cs.pentagoncafe.flare.service;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.ResendTokenDTO;

public interface RegistrationService {
    void register(RegistrationRequestDTO registrationRequestDTO);
    void resendToken(ResendTokenDTO resendTokenDTO);
    void confirmRegistration(String token);
}

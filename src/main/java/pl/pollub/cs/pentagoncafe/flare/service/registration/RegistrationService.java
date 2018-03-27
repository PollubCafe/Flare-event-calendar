package pl.pollub.cs.pentagoncafe.flare.service.registration;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailReqDTO;

public interface RegistrationService {
    void register(RegistrationReqDTO registrationReqDTO);
    void resendToken(EmailReqDTO emailReqDTO);
    void finishRegistration(String token);
}

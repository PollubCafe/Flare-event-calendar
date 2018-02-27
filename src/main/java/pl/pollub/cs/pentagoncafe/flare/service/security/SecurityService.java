package pl.pollub.cs.pentagoncafe.flare.service.security;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.AuthUserResponseDTO;

public interface SecurityService {

    void resetPassword(EmailReqDTO emailReqDTO);

    AuthUserResponseDTO getAuthDataForLoggedUser();

}

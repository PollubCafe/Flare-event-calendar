package pl.pollub.cs.pentagoncafe.flare.service.email;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailToEventAuthorReqDTO;

public interface EmailSenderService {
    void sendEmail(EmailToEventAuthorReqDTO emailToEventAuthorReqDTO);
}

package pl.pollub.cs.pentagoncafe.flare.component.email.sender;


import pl.pollub.cs.pentagoncafe.flare.component.email.HtmlEmail;

import javax.mail.MessagingException;

public interface EmailSender {
    void send(HtmlEmail email) throws MessagingException;
}

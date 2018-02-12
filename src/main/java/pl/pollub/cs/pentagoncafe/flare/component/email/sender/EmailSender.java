package pl.pollub.cs.pentagoncafe.flare.component.email.sender;


import javax.mail.MessagingException;

public interface EmailSender {
    void sendActivationEmail(String recipientAddress, String recipientNick, String activationToken) throws MessagingException;
}

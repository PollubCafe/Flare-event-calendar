package pl.pollub.cs.pentagoncafe.flare.component.email.builder;

import pl.pollub.cs.pentagoncafe.flare.component.email.Email;

public interface EmailBuilder {

    EmailBuilder buildActivationEmail(String activationToken);

    EmailBuilder buildResetPasswordEmail(String temporalPassword);

    EmailBuilder buildEmailToEventAuthor(String messageContent, String subject);

    Email to(String recipientAddress);

}

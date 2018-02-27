package pl.pollub.cs.pentagoncafe.flare.component.email.builder;

import pl.pollub.cs.pentagoncafe.flare.component.email.Email;

public interface EmailBuilder {

    EmailBuilder buildActivationEmail(String activationToken);

    EmailBuilder buildResetPasswordEmail(String temporalPassword);

    Email to(String recipientAddress);

}

package pl.pollub.cs.pentagoncafe.flare.component.email.contentBuilder;

public interface EmailContentBuilder {
    String buildActivationEmail(String activationToken, String recipientNick);
}

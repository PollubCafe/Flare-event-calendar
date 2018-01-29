package pl.pollub.cs.pentagoncafe.flare.service;


public interface EmailService {
    void sendVerificationMail(String recipientAddress, String token);
}

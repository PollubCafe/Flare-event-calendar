package pl.pollub.cs.pentagoncafe.flare.service;


public interface EmailService {
    void sendVerificationEmail(String email, String token);
}

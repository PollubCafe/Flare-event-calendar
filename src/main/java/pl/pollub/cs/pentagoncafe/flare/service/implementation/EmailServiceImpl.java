package pl.pollub.cs.pentagoncafe.flare.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSenderImpl mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationMail(String recipientAddress, String token){
        String content = "http:/localhost:8080/api/registration/confirm?token="+token;
        this.sendMail("from@no-spam.com",recipientAddress,"Confirm registration", content);
    }

    private void sendMail(String from, String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}

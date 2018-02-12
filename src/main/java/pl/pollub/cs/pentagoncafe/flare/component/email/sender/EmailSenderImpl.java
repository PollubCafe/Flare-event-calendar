package pl.pollub.cs.pentagoncafe.flare.component.email.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.component.email.contentBuilder.EmailContentBuilder;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSenderImpl mailSender;
    private final EmailContentBuilder mailContentBuilder;

    @Autowired
    public EmailSenderImpl(JavaMailSenderImpl mailSender, EmailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    public void sendActivationEmail(String recipientAddress, String recipientNick, String activationToken) throws MessagingException{
        String content = mailContentBuilder.buildActivationEmail(activationToken, recipientNick);
        this.sendHTMLEmail("from@no-spam.com",recipientAddress,"Finish registration", content);
    }

    private void sendHTMLEmail(String from, String to, String subject, String body) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body,true);
        mailSender.send(message);
    }
}

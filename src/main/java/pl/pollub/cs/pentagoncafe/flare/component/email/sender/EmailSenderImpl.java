package pl.pollub.cs.pentagoncafe.flare.component.email.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.component.email.HtmlEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Component
public class EmailSenderImpl implements EmailSender {

    private final JavaMailSenderImpl mailSender;

    @Autowired
    public EmailSenderImpl(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public void send(HtmlEmail email) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setFrom(email.getFrom());
        helper.setTo(email.getTo());
        helper.setSubject(email.getSubject());
        helper.setText(email.getHtmlContent(),true);

        mailSender.send(message);
    }
}

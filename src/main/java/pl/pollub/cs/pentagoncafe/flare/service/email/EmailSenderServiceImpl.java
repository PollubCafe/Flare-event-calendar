package pl.pollub.cs.pentagoncafe.flare.service.email;

import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailToEventAuthorReqDTO;
import pl.pollub.cs.pentagoncafe.flare.component.email.HtmlEmail;
import pl.pollub.cs.pentagoncafe.flare.component.email.builder.EmailBuilder;
import pl.pollub.cs.pentagoncafe.flare.component.email.sender.EmailSender;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail.SendingEmailException;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.component.email.Email;

import javax.mail.MessagingException;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    private final EventRepository eventRepository;

    private final EmailSender emailSender;

    private final EmailBuilder emailBuilder;

    public EmailSenderServiceImpl(EventRepository eventRepository, EmailSender emailSender, EmailBuilder emailBuilder){
        this.eventRepository = eventRepository;
        this.emailSender = emailSender;
        this.emailBuilder = emailBuilder;
    }

    @Override
    public void sendEmail(EmailToEventAuthorReqDTO emailToEventAuthorReqDTO) {
        Event event = eventRepository.findById(emailToEventAuthorReqDTO.getEventID())
                .orElseThrow(() -> new ObjectNotFoundException(Event.class, "id", emailToEventAuthorReqDTO.getEventID()));


        User eventAuthor = event.getOrganizer();


        String message = emailToEventAuthorReqDTO.getContent();
        String subject = emailToEventAuthorReqDTO.getSubject();

        Email email = emailBuilder.buildEmailToEventAuthor(message,subject).to(eventAuthor.getEmail());

        try {
            checkArgument(email instanceof HtmlEmail);
            emailSender.send((HtmlEmail) email);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new SendingEmailException("Email to author sending filed.");
        }
    }
}

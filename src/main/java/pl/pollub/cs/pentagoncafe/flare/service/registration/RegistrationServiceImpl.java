package pl.pollub.cs.pentagoncafe.flare.service.registration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailReqDTO;
import pl.pollub.cs.pentagoncafe.flare.component.email.Email;
import pl.pollub.cs.pentagoncafe.flare.component.email.HtmlEmail;
import pl.pollub.cs.pentagoncafe.flare.component.email.builder.EmailBuilder;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.ActivationToken;
import pl.pollub.cs.pentagoncafe.flare.exception.*;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.*;
import pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail.SendingActivationEmailFailException;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.component.email.sender.EmailSender;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkArgument;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final EmailSender emailSender;

    private final EmailBuilder emailBuilder;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   EmailSender emailSender,
                                   EmailBuilder emailBuilder,
                                   BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.emailBuilder = emailBuilder;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = {MessagingException.class, ObjectNotFoundException.class})
    public void register(RegistrationReqDTO registrationReqDTO){

        String token = UUID.randomUUID().toString();
        ActivationToken activationToken = new ActivationToken(token);

        User user = User.builder()
                .name(registrationReqDTO.getName())
                .surname(registrationReqDTO.getSurname())
                .nick(registrationReqDTO.getNick())
                .email(registrationReqDTO.getEmail())
                .phoneNumber(registrationReqDTO.getPhoneNumber())
                .password(passwordEncoder.encode(registrationReqDTO.getPassword()))
                .role(Role.DISABLED)
                .activationToken(activationToken)
                .build();

        userRepository.save(user);

        Email activationEmail = emailBuilder.buildActivationEmail(token).to(user.getEmail());

        this.sendActivationEmail(activationEmail);
    }

    @Transactional(rollbackFor = {MessagingException.class, ObjectNotFoundException.class})
    public void resendToken(EmailReqDTO emailReqDTO){

        User user= userRepository.findByEmail(emailReqDTO.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException(User.class,"e-mail", emailReqDTO.getEmail()));

        if(user.isEnabled())
            throw new UserAlreadyActivatedException(user.getNick());

        String newToken = UUID.randomUUID().toString();
        ActivationToken newActivationToken = user.getActivationToken();
        newActivationToken.setToken(newToken);
        newActivationToken.refreshDate();

        userRepository.save(user);

        Email repeatedActivationEmail = emailBuilder.buildActivationEmail(newToken).to(user.getEmail());

        this.sendActivationEmail(repeatedActivationEmail);
    }

    public void finishRegistration(String token){

        User user = userRepository.findByActivationToken(token).orElseThrow(() ->
                new ObjectNotFoundException(User.class));

        if(user.isEnabled())
            throw new UserAlreadyActivatedException(user.getNick());

        ActivationToken activationToken = user.getActivationToken();

        if (activationToken.getExpirationDate().isBefore(Instant.now())) {
            throw new TokenExpiredException();
        }

        user.setEnabled(true);
        user.setRole(Role.USER);

        userRepository.save(user);
    }

    private void sendActivationEmail(Email email){
        try {
            checkArgument(email instanceof HtmlEmail);
            emailSender.send((HtmlEmail) email);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new SendingActivationEmailFailException();
        }
    }
}

package pl.pollub.cs.pentagoncafe.flare.service.registration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.ResendTokenDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.ActivationToken;
import pl.pollub.cs.pentagoncafe.flare.exception.*;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.*;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.component.email.sender.EmailSender;

import javax.mail.MessagingException;
import java.time.Instant;
import java.util.Calendar;
import java.util.UUID;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmailSender emailSender;

    public RegistrationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EmailSender emailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }


    public void register(RegistrationRequestDTO registrationRequestDTO){
        userRepository.findByNick(registrationRequestDTO.getNick()).ifPresent((user) -> {throw new UserWithThisNickAlreadyExistException(user.getNick());});
        userRepository.findByEmail(registrationRequestDTO.getEmail()).ifPresent((user) -> {throw new UserWithThisEmailAlreadyExistException(user.getEmail());});

        String token = UUID.randomUUID().toString();
        ActivationToken activationToken = new ActivationToken(token);

        User user = User.builder()
                .name(registrationRequestDTO.getName())
                .surname(registrationRequestDTO.getSurname())
                .nick(registrationRequestDTO.getNick())
                .email(registrationRequestDTO.getEmail())
                .phoneNumber(registrationRequestDTO.getPhoneNumber())
                .password(passwordEncoder.encode(registrationRequestDTO.getPassword()))
                .role(Role.DISABLED)
                .activationToken(activationToken)
                .build();

        userRepository.save(user);

        this.sendActivationEmail(user.getEmail(),user.getNick(),token);
    }

    public void resendToken(ResendTokenDTO resendTokenDTO){

        User user= userRepository.findByEmail(resendTokenDTO.getEmail())
                .orElseThrow(() -> new ObjectNotFoundException(User.class,"e-mail",resendTokenDTO.getEmail()));

        if(user.isEnabled())
            throw new UserAlreadyActivatedException(user.getNick());

        ActivationToken newToken = user.getActivationToken();
        newToken.setToken(UUID.randomUUID().toString());
        newToken.refreshDate();

        userRepository.save(user);

        String recipientAddress = user.getEmail();

        this.sendActivationEmail(recipientAddress,user.getNick(),newToken.getToken());
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

    private void sendActivationEmail(String recipientAddress, String recipientNick, String activationToken){
        try {
            emailSender.sendActivationEmail(recipientAddress,recipientNick,activationToken);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new SendingActivationEMailFailException();
        }
    }
}

package pl.pollub.cs.pentagoncafe.flare.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.AuthUserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.security.UserDetailsImpl;
import pl.pollub.cs.pentagoncafe.flare.component.email.Email;
import pl.pollub.cs.pentagoncafe.flare.component.email.HtmlEmail;
import pl.pollub.cs.pentagoncafe.flare.component.email.builder.EmailBuilder;
import pl.pollub.cs.pentagoncafe.flare.component.email.sender.EmailSender;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.exception.ResetPasswordException;
import pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail.SendingResetPasswordEmailFailException;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.security.randomPassword.RandomPasswordGenerator;

import javax.mail.MessagingException;
import static com.google.common.base.Preconditions.checkArgument;

@Component
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    private final EmailSender emailSender;

    private final EmailBuilder emailBuilder;

    private final Messages messages;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public SecurityServiceImpl(UserRepository userRepository,
                               EmailSender emailSender,
                               EmailBuilder emailBuilder,
                               Messages messages, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.emailBuilder = emailBuilder;
        this.messages = messages;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(
            rollbackFor = {
                    MessagingException.class,
                    ObjectNotFoundException.class,
                    ResetPasswordException.class
            })
    public void resetPassword(EmailReqDTO emailReqDTO) {
        String recipientAddress = emailReqDTO.getEmail();
        User user = userRepository.findByEmail(recipientAddress)
                .orElseThrow(() -> new ObjectNotFoundException(User.class,"e-mail", emailReqDTO.getEmail()));

        if(!user.isEnabled()){
            throw new ResetPasswordException(messages.get("login.userAccount.disabled"));
        }
        if(user.isBanned()){
            throw new ResetPasswordException(messages.get("login.userAccount.locked"));
        }

        String generatedPassword = RandomPasswordGenerator.generate();
        user.setPassword(passwordEncoder.encode(generatedPassword));
        userRepository.save(user);

        Email resetPasswordEmail = emailBuilder.buildResetPasswordEmail(generatedPassword).to(recipientAddress);

        this.sendResetPasswordEmail(resetPasswordEmail);
    }

    @Override
    public AuthUserResponseDTO getAuthDataForLoggedUser(){
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication();

        String nick = userDetails.getUsername();
        String email = userDetails.getEmail();
        String role = userDetails.getRole().name();

        return new AuthUserResponseDTO(nick,email,role);
    }

    private void sendResetPasswordEmail(Email email){
        try{
            checkArgument(email instanceof HtmlEmail);
            emailSender.send((HtmlEmail)email);
        } catch(MessagingException e){
            e.printStackTrace();
            throw new SendingResetPasswordEmailFailException();
        }
    }
}

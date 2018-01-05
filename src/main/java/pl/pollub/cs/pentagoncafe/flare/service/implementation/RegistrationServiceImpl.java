package pl.pollub.cs.pentagoncafe.flare.service.implementation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.ResendTokenDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.VerificationToken;
import pl.pollub.cs.pentagoncafe.flare.exception.*;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.PasswordConfirmNotMatchToPassword;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.TokenExpired;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.UserWithThisEmailAlreadyExist;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.UserWithThisNickAlreadyExist;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.EmailService;
import pl.pollub.cs.pentagoncafe.flare.service.RegistrationService;

import java.util.Calendar;
import java.util.UUID;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final EmailService emailService;

    public RegistrationServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }


    public void register(RegistrationRequestDTO registrationRequestDTO){
        userRepository.findByNick(registrationRequestDTO.getNick()).ifPresent((user) -> {throw new UserWithThisNickAlreadyExist(user.getNick());});
        userRepository.findByNick(registrationRequestDTO.getEmail()).ifPresent((user) -> {throw new UserWithThisEmailAlreadyExist(user.getEmail());});

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token);

        User user = User.builder()
                .name(registrationRequestDTO.getName())
                .surname(registrationRequestDTO.getSurname())
                .nick(registrationRequestDTO.getNick())
                .email(registrationRequestDTO.getEmail())
                .phoneNumber(registrationRequestDTO.getPhoneNumber())
                .password(passwordEncoder.encode(registrationRequestDTO.getPassword()))
                .verificationToken(verificationToken)
                .build();

        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(),token);
    }

    public void resendToken(ResendTokenDTO resendTokenDTO){

        User user= userRepository.findByEmailAndEnabled(resendTokenDTO.getEmail(),false)
                .orElseThrow(() -> new ObjectNotFoundException(User.class,"e-mail",resendTokenDTO.getEmail()));

        VerificationToken newToken = user.getVerificationToken();
        newToken.setToken(UUID.randomUUID().toString());
        newToken.refreshDate();

        userRepository.save(user);

        String recipientAddress = user.getEmail();

        emailService.sendVerificationEmail(recipientAddress,newToken.getToken());
    }

    public void confirmRegistration(String token){

        User user = userRepository.findByVerificationToken(token).orElseThrow(() -> new ObjectNotFoundException(
                        new StringBuilder("User account with token: ").append(token).append(" not found").toString()));

        VerificationToken verificationToken = user.getVerificationToken();

        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpirationDate() - cal.getTime().getTime()) <= 0) {
            throw new TokenExpired();
        }

        user.setVerificationToken(null);
        user.setEnabled(true);

        userRepository.save(user);

        //return html page
    }
}

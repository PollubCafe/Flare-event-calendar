package pl.pollub.cs.pentagoncafe.flare.component.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationReqDTO;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

@Component
public class RegistrationReqDTOValidator implements Validator{

    private final UserRepository userRepository;

    private final Messages messages;

    @Autowired
    public RegistrationReqDTOValidator(UserRepository userRepository, Messages messages) {
        this.userRepository = userRepository;
        this.messages = messages;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationReqDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationReqDTO registrationReqDTO = (RegistrationReqDTO) o;

        boolean nickAlreadyUsed  = userRepository.findByNick(registrationReqDTO.getNick()).orElse(null) != null;

        if(nickAlreadyUsed){
            String errorCode = "validation.registration.nick.alreadyUsed";
            errors.rejectValue("nick",errorCode,messages.get(errorCode));
        }

        boolean emailAlreadyUsed  = userRepository.findByEmail(registrationReqDTO.getEmail()).orElse(null) != null;

        if(emailAlreadyUsed){
            String errorCode = "validation.registration.email.alreadyUsed";
            errors.rejectValue("email",errorCode,messages.get(errorCode));
        }
    }
}

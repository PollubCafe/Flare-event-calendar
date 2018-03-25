package pl.pollub.cs.pentagoncafe.flare.component.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventReqDTO;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import org.springframework.validation.Errors;

@Component
public class CreateEventReqDTOValidator implements Validator{

    private final EventRepository eventRepository;

    private final Messages messages;

    @Autowired
    public CreateEventReqDTOValidator(EventRepository eventRepository, Messages messages){
        this.eventRepository=eventRepository;
        this.messages=messages;
    }

    @Override
    public boolean supports(Class<?> aClass){
        return CreateEventReqDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        CreateEventReqDTO createEventReqDTO = (CreateEventReqDTO) o;

        boolean titleAlreadyUsed = eventRepository.findByTitle(createEventReqDTO.getTitle()).orElse(null) != null;

        if(titleAlreadyUsed){
            String errorCode = "validation.createEvent.title.alreadyUsed";
            errors.rejectValue("title", errorCode, messages.get(errorCode));
        }
    }
}

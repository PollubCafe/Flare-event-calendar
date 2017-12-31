package pl.pollub.cs.pentagoncafe.flare.mapper;

import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.DTO.EventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;

@Component
public class EventToResponseMapper {
    public EventResponseDTO map(Event event){
        return EventResponseDTO.builder().
                id(event.getId().toString()).
                creatorName(event.getCreator().getName()).
                title(event.getTitle()).
                description(event.getDescription()).
                status(event.getStatus()).
                town(event.getTown()).
                zipCode(event.getZipCode()).
                street(event.getStreet()).
                block(event.getBloc()).
                houseNumber(event.getHouseNumber()).
                week(event.getWeek()).
                year(event.getYear()).
                duration(event.getDuration()).
                date(event.getDate()).
                onlyUser(event.isOnlyForRegisteredUsers()).
                build();
    }
}

package pl.pollub.cs.pentagoncafe.flare.service;

import org.springframework.data.domain.Page;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreatedEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;

public interface EventService {
    Event createEvent(CreatedEventRequestDTO event);
    Page<Event> getPageOfNotApprovedEventsByPageNumber(int pageNumber);
}

package pl.pollub.cs.pentagoncafe.flare.service.event;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;

public interface EventService {
    SimplifiedEventResponseDTO createEvent(CreateEventRequestDTO event);
    PageResponseDTO<SimplifiedEventResponseDTO> getPageOfNotApprovedEventsByPageNumber(int pageNumber);
}

package pl.pollub.cs.pentagoncafe.flare.controller;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreatedEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.mapper.EventToSimplifiedEventResponseDTOMapper;
import pl.pollub.cs.pentagoncafe.flare.service.EventService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventToSimplifiedEventResponseDTOMapper eventToEventResponseDTOMapper;
    private final EventService eventService;

    public EventController(EventToSimplifiedEventResponseDTOMapper eventToResponseMapper, EventService eventService) {
        this.eventToEventResponseDTOMapper = eventToResponseMapper;
        this.eventService = eventService;
    }


    @GetMapping()
    public ResponseEntity<PageResponseDTO<SimplifiedEventResponseDTO>>
    getPageOfNotApprovedEventsByPageNumber(@RequestParam("pageNumber") int pageNumber){
        Page<Event> eventsPage = eventService.getPageOfNotApprovedEventsByPageNumber(pageNumber);

        PageResponseDTO<SimplifiedEventResponseDTO> pageResponseDTO = new PageResponseDTO<>(
                eventsPage.getTotalPages(),
                eventsPage.getNumber(),
                eventsPage.getContent().stream().map(eventToEventResponseDTOMapper::map).collect(Collectors.toList())
        );

        return new ResponseEntity<>(
                pageResponseDTO,
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<SimplifiedEventResponseDTO> createEvent(@RequestBody @NonNull @Valid CreatedEventRequestDTO eventRequestDTO){
        return new ResponseEntity<>(eventToEventResponseDTOMapper.map(eventService.createEvent(eventRequestDTO)), HttpStatus.OK);
    }
}

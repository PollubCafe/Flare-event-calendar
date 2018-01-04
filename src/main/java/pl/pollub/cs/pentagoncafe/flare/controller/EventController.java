package pl.pollub.cs.pentagoncafe.flare.controller;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PageResponseDTO> getPageOfNotApprovedEventsByPageNumber(@RequestParam("pageNumber") int pageNumber){
        Page<Event> eventsPage = eventService.getPageOfNotApprovedEventsByPageNumber(pageNumber);

        return new ResponseEntity<>(
                PageResponseDTO.builder()
                .totalPages(eventsPage.getTotalPages())
                .currentPageNumber(eventsPage.getNumber())
                .content(eventsPage.getContent().stream().map(eventToEventResponseDTOMapper::map).collect(Collectors.toList()))
                .build(),
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<SimplifiedEventResponseDTO> createEvent(@RequestBody @NonNull @Valid CreatedEventRequestDTO eventRequestDTO){
        return new ResponseEntity<>(eventToEventResponseDTOMapper.map(eventService.createEvent(eventRequestDTO)), HttpStatus.OK);
    }
}

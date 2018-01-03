package pl.pollub.cs.pentagoncafe.flare.controller;

import lombok.NonNull;
import org.springframework.data.domain.Page;
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
    public PageResponseDTO getPageOfNotApprovedEventsByPageNumber(@RequestParam("pageNumber") int pageNumber){
        Page<Event> eventsPage = eventService.getPageOfNotApprovedEventsByPageNumber(pageNumber);

        return PageResponseDTO.builder()
                .totalPages(eventsPage.getTotalPages())
                .currentPageNumber(eventsPage.getNumber())
                .content(eventsPage.getContent().stream().map(eventToEventResponseDTOMapper::map).collect(Collectors.toList()))
                .build();

    }

    @PostMapping()
    public SimplifiedEventResponseDTO createEvent(@RequestBody @NonNull @Valid CreatedEventRequestDTO eventRequestDTO){
        return eventToEventResponseDTOMapper.map(eventService.createEvent(eventRequestDTO));
    }
}

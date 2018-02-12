package pl.pollub.cs.pentagoncafe.flare.controller;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.mapper.EventMapper;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventService;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<PageResponseDTO<SimplifiedEventResponseDTO>> getPageOfNotApprovedEventsByPageNumber(
            @PathVariable("pageNumber") int pageNumber){
        PageResponseDTO<SimplifiedEventResponseDTO> pageResponseDTO = eventService.getPageOfNotApprovedEventsByPageNumber(pageNumber);
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @PostMapping()
    public ResponseEntity<SimplifiedEventResponseDTO> createEvent(@RequestBody @NonNull @Valid CreateEventRequestDTO eventRequestDTO){
        return new ResponseEntity<>(eventService.createEvent(eventRequestDTO), HttpStatus.OK);
    }
}

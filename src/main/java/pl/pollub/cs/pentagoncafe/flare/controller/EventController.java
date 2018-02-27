package pl.pollub.cs.pentagoncafe.flare.controller;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/12/12
 */
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    public ResponseEntity<SimplifiedEventResponseDTO> createEvent(@RequestBody @Valid @NotNull CreateEventReqDTO eventRequestDTO){
        return new ResponseEntity<>(eventService.createEvent(eventRequestDTO), HttpStatus.OK);
    }
}

package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.EventResDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.service.event.EventService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<EventResDTO> showEvent(@PathVariable("id") String id) {
        EventResDTO eventToShow = eventService.readEvent(id);

        return new ResponseEntity<>(eventToShow, HttpStatus.OK);
    }

    @GetMapping("/page/{pageNumber}")
    public ResponseEntity<PageResponseDTO<SimplifiedEventResponseDTO>> getPageOfEventsByPageNumber(
            @PathVariable("pageNumber") int pageNumber){
        PageResponseDTO<SimplifiedEventResponseDTO> pageResponseDTO = eventService.getPageOfEventsByPageNumber(pageNumber);
        return new ResponseEntity<>(pageResponseDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<SimplifiedEventResponseDTO> createEvent(@RequestBody @Valid @NotNull CreateEventReqDTO eventRequestDTO){
        return new ResponseEntity<>(eventService.createEvent(eventRequestDTO), HttpStatus.OK);
    }

    @GetMapping("/new")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> getNewEvents(){
        return new ResponseEntity<>(eventService.getNewEvents(), HttpStatus.OK);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> getApprovedEvents(){
        return new ResponseEntity<>(eventService.getApprovedEvents(), HttpStatus.OK);
    }

    @GetMapping("/ended")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> getEndedEvents(){
        return new ResponseEntity<>(eventService.getEventsWithEndedRegistration(), HttpStatus.OK);
    }

    @GetMapping("/{userNick}/attending")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> getUserAttendingEvents(@PathVariable String userNick){
        return new ResponseEntity<>(eventService.getEventsWhichUserIsAttending(userNick), HttpStatus.OK);
    }

    @GetMapping("/{userNick}/created")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> getEventsOfUser(@PathVariable String userNick){
        List<SimplifiedEventResponseDTO> events = eventService.getEventsWhichWasCreatedByUser(userNick);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/new")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> adminListOfNewEvents(){
        return new ResponseEntity<>(eventService.adminGetNewEvents(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/approved")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> adminListOfApprovedEvents(){
        return new ResponseEntity<>(eventService.adminGetApprovedEvents(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/{userNick}/attending")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> adminListOfEventsThatUserIsAttending(@PathVariable String userNick){
        return new ResponseEntity<>(eventService.adminGetEventWhichUserIsAttending(userNick), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/{userNick}/created")
    public ResponseEntity<List<SimplifiedEventResponseDTO>> adminListEventsThatWasCreatedByUser(@PathVariable String userNick){
        return new ResponseEntity<>(eventService.adminGetEventsWhichWasCreatedByUser(userNick), HttpStatus.OK);
    }

}

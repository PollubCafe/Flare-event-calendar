package pl.pollub.cs.pentagoncafe.flare.service.event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.*;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Province;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.mapper.EventMapper;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final EventMapper eventMapper;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventMapper = eventMapper;
    }

    @Override
    public SimplifiedEventResponseDTO createEvent(CreateEventRequestDTO createEventRequestDTO) {
        String authenticatedUserNick = SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository.findByNick(authenticatedUserNick).orElseThrow(()->new ObjectNotFoundException(User.class,"nick",authenticatedUserNick));

        Address address = Address.builder()
                .town(createEventRequestDTO.getAddress_town())
                .zipCode(createEventRequestDTO.getAddress_zipCode())
                .street(createEventRequestDTO.getAddress_street())
                .province(Province.valueOf(createEventRequestDTO.getAddress_province()))
                .blockNumber(createEventRequestDTO.getAddress_blockNumber())
                .houseNumber(createEventRequestDTO.getAddress_houseNumber())
                .additionalInformation(createEventRequestDTO.getAddress_additionalInformation())
                .build();

        Event event = Event.builder()
                .address(address)
                .title(createEventRequestDTO.getTitle())
                .description(createEventRequestDTO.getDescription())
                .duration(createEventRequestDTO.getDuration())
                .dateOfEventApproval(createEventRequestDTO.getDateOfEventApproval().toInstant())
                .isApproved(false)
                .onlyForRegisteredUsers(createEventRequestDTO.isOnlyForRegisteredUsers())
                .dateOfCreation(Instant.now())
                .build();

        authenticatedUser.addEvent(event);

        Event createdEvent = eventRepository.save(event);
        userRepository.save(authenticatedUser);
        return eventMapper.mapToResponseDTO(createdEvent);
    }

    @Override
    public PageResponseDTO<SimplifiedEventResponseDTO> getPageOfNotApprovedEventsByPageNumber(int pageNumber) {
        int defaultPageSize = 7;
        String sortBy = "dateOfCreation";
        PageRequest pageRequest = new PageRequest(pageNumber, defaultPageSize, Sort.Direction.DESC,sortBy);
        Page<Event> eventsPage = eventRepository.getPageOfNotApprovedEventsByPageNumber(pageRequest);

        return new PageResponseDTO<>(
                eventsPage.getTotalPages(),
                eventsPage.getNumber(),
                eventsPage.getContent().stream().map(eventMapper::mapToResponseDTO).collect(Collectors.toList())
        );
    }
}

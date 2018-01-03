package pl.pollub.cs.pentagoncafe.flare.service.implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreatedEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.*;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.repository.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.VoteRepository;
import pl.pollub.cs.pentagoncafe.flare.service.EventService;

import java.util.Date;

@Service
public class EventServiceImpl implements EventService {

    private final int DEFAULT_PAGE_SIZE = 7;

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Event createEvent(CreatedEventRequestDTO createdEventRequestDTO) {
        User mockOrganizer = userRepository.findByNick("Barabasz").orElseThrow(()->new ObjectNotFoundException(User.class,"Barabasz"));

        Address address = Address.builder()
                .town(createdEventRequestDTO.getAddress_town())
                .zipCode(createdEventRequestDTO.getAddress_zipCode())
                .street(createdEventRequestDTO.getAddress_street())
                .province(Province.valueOf(createdEventRequestDTO.getAddress_province()))
                .blockNumber(createdEventRequestDTO.getAddress_blockNumber())
                .houseNumber(createdEventRequestDTO.getAddress_houseNumber())
                .additionalInformation(createdEventRequestDTO.getAddress_additionalInformation())
                .build();

        Event event = Event.builder()
                .organizer(mockOrganizer)
                .address(address)
                .title(createdEventRequestDTO.getTitle())
                .description(createdEventRequestDTO.getDescription())
                .duration(createdEventRequestDTO.getDuration())
                .dateOfEventApproval(createdEventRequestDTO.getDateOfEventApproval())
                .isApproved(false)
                .onlyForRegisteredUsers(createdEventRequestDTO.isOnlyForRegisteredUsers())
                .dateOfCreation(new Date())
                .build();


        Event createdEvent = eventRepository.save(event);
        mockOrganizer.getOrganizedEvents().add(createdEvent);
        userRepository.save(mockOrganizer);
        return createdEvent;
    }

    @Override
    public Page<Event> getPageOfNotApprovedEventsByPageNumber(int pageNumber) {
        return eventRepository.getPageOfNotApprovedEventsByPageNumber(new PageRequest(pageNumber,DEFAULT_PAGE_SIZE, Sort.Direction.ASC,"dateOfCreation"));
    }
}

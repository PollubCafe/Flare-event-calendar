package pl.pollub.cs.pentagoncafe.flare.service.event;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.EventResDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.security.UserDetailsImpl;
import pl.pollub.cs.pentagoncafe.flare.domain.*;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.EventStatus;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Province;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.mapper.EventMapper;
import pl.pollub.cs.pentagoncafe.flare.repository.event.EventRepository;
import pl.pollub.cs.pentagoncafe.flare.repository.user.UserRepository;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePoint;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePointType;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public EventResDTO readEvent(String id) {
        Event foundEvent = eventRepository.findById(new ObjectId(id)).orElseThrow(()->new ObjectNotFoundException(Event.class,"id", id));

        return eventMapper.mapToResDTO(foundEvent);
    }

    @Override
    public SimplifiedEventResponseDTO createEvent(CreateEventReqDTO createEventReqDTO) {
        String authenticatedUserNick = SecurityContextHolder.getContext().getAuthentication().getName();

        User authenticatedUser = userRepository.findByNick(authenticatedUserNick).orElseThrow(()->new ObjectNotFoundException(User.class,"nick",authenticatedUserNick));

        Address address = Address.builder()
                .town(createEventReqDTO.getAddress_town())
                .zipCode(createEventReqDTO.getAddress_zipCode())
                .street(createEventReqDTO.getAddress_street())
                .province(Province.valueOf(createEventReqDTO.getAddress_province()))
                .blockNumber(createEventReqDTO.getAddress_blockNumber())
                .houseNumber(createEventReqDTO.getAddress_houseNumber())
                .additionalInformation(createEventReqDTO.getAddress_additionalInformation())
                .build();

        Event event = Event.builder()
                .address(address)
                .title(createEventReqDTO.getTitle())
                .description(createEventReqDTO.getDescription())
                .duration(createEventReqDTO.getDuration())
                .dateOfEndRegistration(createEventReqDTO.getDateOfEndRegistration().toInstant())
                .status(EventStatus.NEW)
                .onlyForRegisteredUsers(createEventReqDTO.isOnlyForRegisteredUsers())
                .dateOfCreation(Instant.now())
                .build();

        authenticatedUser.addEvent(event);

        Event createdEvent = eventRepository.save(event);
        userRepository.save(authenticatedUser);
        return eventMapper.mapToResponseDTO(createdEvent);
    }

    @Override
    public PageResponseDTO<SimplifiedEventResponseDTO> getPageOfEventsByPageNumber(int pageNumber) {
        int defaultPageSize = 7;
        String sortBy = "dateOfCreation";
        PageRequest pageRequest = new PageRequest(pageNumber, defaultPageSize, Sort.Direction.DESC,sortBy);
        Page<Event> eventsPage = eventRepository.findAll(pageRequest);

        return new PageResponseDTO<>(
                eventsPage.getTotalPages(),
                eventsPage.getNumber(),
                eventsPage.getContent().stream().map(eventMapper::mapToResponseDTO).collect(Collectors.toList())
        );
    }

    @Override
    public Event generateStatisticForEvent(Event event){
        List<TimePoint> timePointsForParticipations = getTimePointsForParticipations(event.getParticipation());
        Set<Term> eventStatistic = generateStatisticForTimePoints(timePointsForParticipations,event.getParticipation().size());
        event.setEventStatistic(eventStatistic);
        return event;
    }

    @Override
    public Set<Term> generateStatisticForTimePoints(List<TimePoint> timePoints,int participantCount){

        Set<Term> termList=new HashSet<>();
        TimePoint leftTimePoint=null;
        int participantCurrentCount=0;

        for(TimePoint tp:timePoints){
            if(Objects.isNull(leftTimePoint)){
                leftTimePoint=tp;
                participantCurrentCount++;
            }else{
                if(leftTimePoint.getDayOfTheWeek().equals(tp.getDayOfTheWeek())
                        && participantCurrentCount>0 && !leftTimePoint.getTime().equals(tp.getTime())){
                    Term term=new Term(leftTimePoint.getTime(),tp.getTime(),participantCurrentCount,
                            leftTimePoint.getDayOfTheWeek(),((double) participantCurrentCount /participantCount)*100);
                    termList.add(term);
                }

                leftTimePoint=tp;

                if(tp.getType()==TimePointType.START) participantCurrentCount++ ;
                else participantCurrentCount--;
            }
        }

        return termList;
    }

    @Override
    public List<TimePoint> getTimePointsForParticipations(Set<Participation> participations) {
        return participations.stream().flatMap(p->p.getVotes().stream()).flatMap(v -> Stream.of(
                    new TimePoint(v.getDayOfWeek(), v.getFrom(), TimePointType.START),
                    new TimePoint(v.getDayOfWeek(), v.getTo(), TimePointType.END)))
                    .sorted().collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> getNewEvents() {
        List<Event> events = eventRepository.getEventsByBannedIsFalseAndStatusIs(EventStatus.REGISTRATION);
        return events.stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> getApprovedEvents() {
        List<Event> events = eventRepository.getEventsByBannedIsFalseAndStatusIs(EventStatus.APPROVED);
        return events.stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> getEventsWithEndedRegistration() {
        List<Event> events = eventRepository.getEventsByBannedIsFalseAndStatusIs(EventStatus.CLOSED);
        return events.stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> getEventsWhichUserIsAttending(String userNick) {

        User user = userRepository.findByNick(userNick).orElseThrow( () ->  new ObjectNotFoundException(User.class, "nick", userNick) );

        return user.getParticipation()
                .stream()
                .map(Participation::getEvent)
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> getEventsWhichWasCreatedByUser(String userNick) {

        User user = userRepository.findByNick(userNick).orElseThrow( () ->  new ObjectNotFoundException(User.class, "nick", userNick) );

        return eventRepository.getEventsByOrganizerAndBannedIsFalse(user)
                .stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> adminGetNewEvents() {
        return eventRepository.getEventsByStatusIs(EventStatus.REGISTRATION)
                .stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> adminGetApprovedEvents() {
        return eventRepository.getEventsByStatusIs(EventStatus.APPROVED)
                .stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> adminGetEventWhichUserIsAttending(String userNick) {

        User user = userRepository.findByNick(userNick).orElseThrow( () ->  new ObjectNotFoundException(User.class, "nick", userNick) );

        return user.getParticipation()
                .stream()
                .map(Participation::getEvent)
                .filter(x -> x.getStatus() == EventStatus.NEW)
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SimplifiedEventResponseDTO> adminGetEventsWhichWasCreatedByUser(String userNick) {

        User user = userRepository.findByNick(userNick).orElseThrow( () ->  new ObjectNotFoundException(User.class, "nick", userNick) );

        return eventRepository.getEventsByOrganizer(user)
                .stream()
                .map(eventMapper::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEvent(ObjectId id) {
        String authenticatedUserNick = SecurityContextHolder.getContext().getAuthentication().getName();
        UserDetailsImpl authenticatedUser = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Event.class));

        if(authenticatedUserNick.equals(event.getOrganizer().getNick()) || authenticatedUser.getRole() == Role.ADMIN)
            eventRepository.delete(id);
    }
}

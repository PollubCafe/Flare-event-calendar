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
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePoint;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePointType;

import javax.validation.executable.ValidateOnExecution;
import java.time.Instant;
import java.util.*;
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
                            leftTimePoint.getDayOfTheWeek(),(Double.valueOf(participantCurrentCount)/participantCount)*100);
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
        return participations.stream().flatMap(p->p.getVotes().stream()).flatMap(v -> Arrays.asList(
                    new TimePoint(v.getDayOfWeek(), v.getFrom(), TimePointType.START),
                    new TimePoint(v.getDayOfWeek(), v.getTo(), TimePointType.END)).stream())
                    .sorted().collect(Collectors.toList());
    }
}

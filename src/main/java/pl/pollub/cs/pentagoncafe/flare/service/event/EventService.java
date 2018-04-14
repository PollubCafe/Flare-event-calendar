package pl.pollub.cs.pentagoncafe.flare.service.event;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.EventResDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;
import pl.pollub.cs.pentagoncafe.flare.domain.Term;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePoint;

import java.util.List;
import java.util.Set;

public interface EventService {

    EventResDTO readEvent(String id);

    SimplifiedEventResponseDTO createEvent(CreateEventReqDTO event);
    PageResponseDTO<SimplifiedEventResponseDTO> getPageOfEventsByPageNumber(int pageNumber);

    Event generateStatisticForEvent(Event event);

    Set<Term> generateStatisticForTimePoints(List<TimePoint> timePoints, int participantCount);

    List<TimePoint> getTimePointsForParticipations(Set<Participation> participations);

    List<SimplifiedEventResponseDTO> getNewEvents();
    List<SimplifiedEventResponseDTO> getApprovedEvents();
    List<SimplifiedEventResponseDTO> getEventsWithEndedRegistration();

    List<SimplifiedEventResponseDTO> getEventsWhichUserIsAttending(String userNick);
    List<SimplifiedEventResponseDTO> getEventsWhichWasCreatedByUser(String userNick);

    List<SimplifiedEventResponseDTO> adminGetNewEvents();
    List<SimplifiedEventResponseDTO> adminGetApprovedEvents();
    List<SimplifiedEventResponseDTO> adminGetEventWhichUserIsAttending(String userNick);
    List<SimplifiedEventResponseDTO> adminGetEventsWhichWasCreatedByUser(String userNick);

}

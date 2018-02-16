package pl.pollub.cs.pentagoncafe.flare.service.event;

import pl.pollub.cs.pentagoncafe.flare.DTO.request.CreateEventRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.PageResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;
import pl.pollub.cs.pentagoncafe.flare.domain.Term;
import pl.pollub.cs.pentagoncafe.flare.domain.Vote;
import pl.pollub.cs.pentagoncafe.flare.service.event.related.TimePoint;

import java.util.List;
import java.util.Set;

public interface EventService {
    SimplifiedEventResponseDTO createEvent(CreateEventRequestDTO event);
    PageResponseDTO<SimplifiedEventResponseDTO> getPageOfNotApprovedEventsByPageNumber(int pageNumber);

    Event generateStatisticForEvent(Event event);

    Set<Term> generateStatisticForTimePoints(List<TimePoint> timePoints, int participantCount);

    List<TimePoint> getTimePointsForParticipations(Set<Participation> participations);
}

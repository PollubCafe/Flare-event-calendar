package pl.pollub.cs.pentagoncafe.flare.repository.event;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import java.util.Optional;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.EventStatus;

import java.util.List;

@Repository
@Transactional
public interface EventRepository extends MongoRepository<Event,ObjectId> {
    Optional<Event> findByTitle(String event);
    Optional<Event> findById(ObjectId id);

    List<Event> getEventsByBannedIsFalseAndStatusIs(EventStatus status);

    List<Event> getEventsByStatusIs(EventStatus status);

    List<Event> getEventsByOrganizer(User user);

    List<Event> getEventsByOrganizerAndBannedIsFalse(User user);

}

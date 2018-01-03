package pl.pollub.cs.pentagoncafe.flare.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
@Repository
@Transactional
public interface EventRepository extends MongoRepository<Event,ObjectId> {
    //Uzywajcie Optional
    @Query(value="{ 'isApproved' : false }")
    Page<Event> getPageOfNotApprovedEventsByPageNumber(Pageable page);
}

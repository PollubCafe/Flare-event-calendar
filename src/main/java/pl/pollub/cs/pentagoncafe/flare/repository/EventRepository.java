package pl.pollub.cs.pentagoncafe.flare.repository;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;

import java.util.List;

@Repository
public interface EventRepository extends MongoRepository<Event,String> {
    Event findById(String id);
    List<Event> findByTitle(String title);
    List<Event> findByPlace(String place);
    List<Event> findByWeek(String week);
    List<Event> findByYear(String year);
    List<Event> findByCreator(String creator);
    List<Event> findByStatus(String status);
}

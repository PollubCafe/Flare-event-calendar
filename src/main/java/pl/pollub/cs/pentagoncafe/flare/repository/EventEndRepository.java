package pl.pollub.cs.pentagoncafe.flare.repository;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.EventEnd;

import java.util.List;

@Repository
public interface EventEndRepository extends MongoRepository<EventEnd,String> {
    EventEnd findById(String id);
    EventEnd findByEvent_id(String id);
    List<EventEnd> findByEvent_idIn(String[] idArray);
}

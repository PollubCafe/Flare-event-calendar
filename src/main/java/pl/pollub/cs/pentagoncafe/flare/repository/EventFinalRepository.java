package pl.pollub.cs.pentagoncafe.flare.repository;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.EventFinal;

@Repository
public interface EventFinalRepository extends MongoRepository<EventFinal,String> {

    EventFinal findByEventid(String eventId);
    EventFinal findByData(String data);

}

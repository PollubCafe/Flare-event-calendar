package pl.pollub.cs.pentagoncafe.flare.repository;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.EventFinal;

import java.util.Date;
import java.util.List;

@Repository
public interface EventFinalRepository extends MongoRepository<EventFinal,String> {

    EventFinal findByEventid(ObjectId eventId);
    List<EventFinal> findByData(Date data);

}

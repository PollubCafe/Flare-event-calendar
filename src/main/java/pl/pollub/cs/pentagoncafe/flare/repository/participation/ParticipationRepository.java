package pl.pollub.cs.pentagoncafe.flare.repository.participation;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;

@Repository
@Transactional
public interface ParticipationRepository extends MongoRepository<Participation, ObjectId> {
}

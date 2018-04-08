package pl.pollub.cs.pentagoncafe.flare.repository.participation;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import pl.pollub.cs.pentagoncafe.flare.domain.Participation;

public interface ParticipationRepository extends MongoRepository<Participation, ObjectId> {
}

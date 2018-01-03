package pl.pollub.cs.pentagoncafe.flare.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.domain.Vote;

@Repository
@Transactional
public interface VoteRepository extends MongoRepository<Vote,ObjectId> {
}

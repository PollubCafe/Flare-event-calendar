package pl.pollub.cs.pentagoncafe.flare.repository.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends MongoRepository<User,ObjectId> {
    Optional<User> findById(ObjectId id);
    Optional<User> findByNick(String nick);
    Optional<User> findByEmail(String email);
    @Query(value="{ 'activationToken.token' : ?0 }")
    Optional<User> findByActivationToken(String token);
}

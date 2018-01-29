package pl.pollub.cs.pentagoncafe.flare.repository;

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
    Optional<User> findByNick(String nick);
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndEnabled(String email,boolean enabled);
    @Query(value="{ 'verificationToken.token' : ?0 }")
    Optional<User> findByVerificationToken(String token);
}

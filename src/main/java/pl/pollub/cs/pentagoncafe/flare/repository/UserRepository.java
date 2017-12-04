package pl.pollub.cs.pentagoncafe.flare.repository;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findById(String id);
    User findByNick(String nick);
    User findByEmail(String ema);

}

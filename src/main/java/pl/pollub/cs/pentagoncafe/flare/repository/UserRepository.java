package pl.pollub.cs.pentagoncafe.flare.repository;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.User;


@Repository
public interface UserRepository extends MongoRepository<User,String> {

    public User findByNick(String nick);
    public User findByEmail(String email);


}

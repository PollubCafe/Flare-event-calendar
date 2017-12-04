package pl.pollub.cs.pentagoncafe.flare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.EventAna;

import java.util.List;

@Repository
public interface EventAnaRepository extends MongoRepository<EventAna,String>{
    EventAna findById(String id);
    EventAna findByEventid(String eventid);
}

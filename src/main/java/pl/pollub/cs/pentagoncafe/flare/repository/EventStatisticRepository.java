package pl.pollub.cs.pentagoncafe.flare.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.pollub.cs.pentagoncafe.flare.domain.EventStatistic;

@Repository
public interface EventStatisticRepository extends MongoRepository<EventStatistic,String>{

    EventStatistic findByEventid(String eventid);
}

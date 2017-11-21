package pl.pollub.cs.pentagoncafe.flare.repository;

import pl.pollub.cs.pentagoncafe.flare.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {

}

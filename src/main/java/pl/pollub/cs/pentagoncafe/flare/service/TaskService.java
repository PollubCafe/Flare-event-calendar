package pl.pollub.cs.pentagoncafe.flare.service;

import pl.pollub.cs.pentagoncafe.flare.domain.Task;

@Deprecated
public interface TaskService {

    Iterable<Task> list();

    Task save(Task task);
}

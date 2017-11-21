package pl.pollub.cs.pentagoncafe.flare.service;

import pl.pollub.cs.pentagoncafe.flare.domain.Task;

public interface TaskService {

    Iterable<Task> list();

    Task save(Task task);
}

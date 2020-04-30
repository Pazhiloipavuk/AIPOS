package lab3.service;

import lab3.model.Task;

import java.util.Collection;

public interface TaskService extends MainService<Task> {

    Collection<Task> findTasksWithoutDescription();
}

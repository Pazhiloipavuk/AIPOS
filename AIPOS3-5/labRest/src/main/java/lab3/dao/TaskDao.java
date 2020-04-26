package lab3.dao;

import lab3.model.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TaskDao extends CrudRepository<Task, Long> {
    Collection<Task> findAllByDescriptionIsNull();
}

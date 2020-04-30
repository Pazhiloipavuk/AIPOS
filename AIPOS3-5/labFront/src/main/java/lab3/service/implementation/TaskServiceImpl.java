package lab3.service.implementation;

import lab3.dao.TaskDao;
import lab3.exception.ExceptionInLab;
import lab3.model.Task;
import lab3.service.DescriptionService;
import lab3.service.TaskService;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    private DescriptionService descriptionService;

    @Autowired
    public TaskServiceImpl(TaskDao taskDao, DescriptionService descriptionService) {
        this.taskDao = taskDao;
        this.descriptionService = descriptionService;
    }

    @Override
    public Collection<Task> findTasksWithoutDescription() {
        return taskDao.findAllByDescriptionIsNull();
    }

    @Override
    public Task save(Task entity) {
        return taskDao.save(entity);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskDao.findById(id);
    }

    @Override
    public List<Task> findAll() {
        return IterableUtils.toList(taskDao.findAll());
    }

    @Override
    public void delete(Task entity) {
        Optional.ofNullable(entity.getDescription()).ifPresent(descriptionService::delete);
        taskDao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id).orElseThrow(ExceptionInLab::new));
    }
}

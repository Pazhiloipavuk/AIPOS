package lab3.controller;

import lab3.model.Task;
import lab3.service.TaskService;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lab3.controller.ControllerConstants.TASKS_URL;

@RestController
@NoArgsConstructor
@RequestMapping(TASKS_URL)
public class TaskController extends AbstractController<Task> {

    private static final String FREE_TASKS_URL = "/free";

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        super(taskService);
        this.taskService = taskService;
    }

    @GetMapping(FREE_TASKS_URL)
    public List<Task> findTasksWithoutDescription() {
        return IterableUtils.toList(taskService.findTasksWithoutDescription());
    }
}

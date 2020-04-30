package lab3.controller.task;

import lab3.controller.ControllerConstants;
import lab3.model.Task;
import lab3.service.TaskService;
import lab3.validator.AbstractValidator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static lab3.controller.ControllerConstants.*;

@Controller
@RequestMapping(FULL_INFORMATION_ABOUT_TASK_URL)
@NoArgsConstructor
@Slf4j
public class ControllerTask {

    private static final String TASK_DETAILS_VIEW = "taskDetails";
    private static final String MODEL_ATTRIBUTE = "task";

    private TaskService taskService;

    private AbstractValidator<Task> validator;

    private ControllerAllTasks controllerAllTasks;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public ControllerTask(TaskService taskService, AbstractValidator<Task> validator,
                          ControllerAllTasks controllerAllTasks) {
        this.taskService = taskService;
        this.validator = validator;
        this.controllerAllTasks = controllerAllTasks;
    }

    @GetMapping
    public String findById(@PathVariable Long id, Model model) {
        log.trace("Find task by id = {}", id);
        taskService.findById(id).ifPresent(model::addAttribute);
        return Optional.ofNullable(model.getAttribute(MODEL_ATTRIBUTE))
                .map(attribute -> TASK_DETAILS_VIEW)
                .orElse(ControllerConstants.ERROR);
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Task task, BindingResult errors, Model model) {
        log.trace("Update task with id = {}", task.getId());
        if (errors.hasErrors()) {
            return TASK_DETAILS_VIEW;
        } else {
            var updatedTask = taskService.save(task);
            return findById(updatedTask.getId(), model);
        }
    }

    @DeleteMapping
    public String delete(@PathVariable Long id, Model model) {
        log.trace("Delete product with id = {}", id);
        taskService.deleteById(id);
        return controllerAllTasks.findAll(model);
    }
}

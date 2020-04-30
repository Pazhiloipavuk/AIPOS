package lab3.controller.description;

import lab3.controller.ControllerConstants;
import lab3.controller.task.ControllerAllTasks;
import lab3.model.Comment;
import lab3.model.Description;
import lab3.service.DescriptionService;
import lab3.service.TaskService;
import lab3.service.UserService;
import lab3.validator.AbstractValidator;
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
import static lab3.controller.task.ControllerAllTasks.TASKS_ATTRIBUTE;
import static lab3.controller.user.ControllerAllUsers.USERS_ATTRIBUTE;

@Controller
@RequestMapping(FULL_INFORMATION_ABOUT_DESCRIPTION_URL)
@Slf4j
public class ControllerDescription {

    private static final String DESCRIPTION_DETAILS_VIEW = "descriptionDetails";
    private static final String MODEL_ATTRIBUTE = "description";

    private DescriptionService descriptionService;

    private ControllerAllDescriptions controllerAllDescriptions;

    private TaskService taskService;

    private AbstractValidator<Description> validator;

    @InitBinder(MODEL_ATTRIBUTE)
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @GetMapping
    public String findById(@PathVariable Long id, Model model) {
        log.trace("Find article by id = {}", id);
        descriptionService.findById(id).ifPresent(description -> {
            fillModel(model);
            model.addAttribute(description);
        });
        return Optional.ofNullable(model.getAttribute(MODEL_ATTRIBUTE))
                .map(attribute -> DESCRIPTION_DETAILS_VIEW)
                .orElse(ControllerConstants.ERROR);
    }

    private void fillModel(Model model) {
        model.addAttribute(new Comment());
        model.addAttribute(ControllerAllTasks.TASKS_ATTRIBUTE, taskService.findTasksWithoutDescription());
    }

    @PutMapping
    public String save(@Validated @ModelAttribute Description description, BindingResult errors, Model model) {
        log.trace("Update article with id = {}", description.getId());
        if (errors.hasErrors()) {
            fillModel(model);
            return DESCRIPTION_DETAILS_VIEW;
        } else {
            var updatedDescription = descriptionService.save(description);
            return findById(updatedDescription.getId(), model);
        }
    }

    @DeleteMapping
    public String delete(@PathVariable Long id, Model model) {
        log.trace("Delete article with id = {}", id);
        descriptionService.deleteById(id);
        return controllerAllDescriptions.findAll(model);
    }

    @Autowired
    public ControllerDescription(DescriptionService descriptionService, ControllerAllDescriptions controllerAllDescriptions,
                                 TaskService taskService, AbstractValidator<Description> validator) {
        this.descriptionService = descriptionService;
        this.controllerAllDescriptions = controllerAllDescriptions;
        this.taskService = taskService;
        this.validator = validator;
    }
}


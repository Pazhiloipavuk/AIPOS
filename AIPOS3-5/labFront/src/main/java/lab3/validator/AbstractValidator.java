package lab3.validator;

import lab3.model.Constants;
import lab3.model.Description;
import lab3.model.Task;
import lab3.model.User;
import lab3.service.DescriptionService;
import lab3.service.TaskService;
import lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@PropertySource("classpath:messages.properties")
public abstract class AbstractValidator<T> implements Validator {

    protected static final String REQUIRED_FIELD = "required.field";
    private static final String DESCRIPTION_NOT_EXISTING = "description.not.existing";
    private static final String TASK_NOT_EXISTING = "task.not.existing";
    private static final String USER_NOT_EXISTING = "user.not.existing";
    private static final String AUTHOR_NOT_VALID = "author.not.valid";

    @Value("${description.not.existing}")
    private String descriptionNotExistingMessage;
    @Value("${task.not.existing}")
    private String taskNotExistingMessage;
    @Value("${user.not.existing}")
    private String userNotExistingMessage;
    @Value("${required.field}")
    private String defaultRequiredMessage;
    @Value("${author.not.valid}")
    private String defaultAuthorNotValid;

    private Class<T> targetClass;

    private DescriptionService descriptionService;

    private TaskService taskService;

    private UserService userService;

    public AbstractValidator(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return targetClass.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Optional.ofNullable(object)
                .filter(target -> supports(object.getClass()))
                .map(targetClass::cast)
                .ifPresent(target -> validateTarget(target, errors));
    }

    protected abstract void validateTarget(T target, Errors errors);

    protected Optional<Description> isExistDescription(Description description, Errors errors) {
        Optional<Description> loadedDescription = Optional.ofNullable(description)
                .stream()
                .map(Description::getId)
                .filter(Objects::nonNull)
                .map(descriptionService::findById)
                .flatMap(Optional::stream)
                .findAny();
        if (loadedDescription.isEmpty()) {
            errors.rejectValue(combine("description", "id"), DESCRIPTION_NOT_EXISTING, descriptionNotExistingMessage);
        }
        return loadedDescription;
    }

    protected Optional<User> isRightAuthor(Long authorId, Errors errors) {
        Optional<User> author = userService.findById(authorId);
        Optional<User> currentUser = userService.getCurrentUser();
        if (!Objects.equals(author, currentUser)) {
            errors.rejectValue(Constants.combine(Constants.AUTHOR, Constants.USER_ID),
                    AUTHOR_NOT_VALID, defaultAuthorNotValid);
        }
        return author;
    }

    protected Optional<Task> isExistTask(Task task, Errors errors) {
        Optional<Task> loadedTask = Optional.ofNullable(task)
                .stream()
                .map(Task::getId)
                .filter(Objects::nonNull)
                .map(taskService::findById)
                .flatMap(Optional::stream)
                .findAny();
        if (loadedTask.isEmpty()) {
            errors.rejectValue(combine("task", "id"), TASK_NOT_EXISTING, taskNotExistingMessage);
        }
        return loadedTask;
    }

    protected Optional<User> isExistUser(User user, Errors errors) {
        Optional<User> loadedUser = Optional.ofNullable(user)
                .stream()
                .map(User::getId)
                .filter(Objects::nonNull)
                .map(userService::findById)
                .flatMap(Optional::stream)
                .findAny();
        if (loadedUser.isEmpty()) {
            errors.rejectValue(combine("author", "id"), USER_NOT_EXISTING, userNotExistingMessage);
        }
        return loadedUser;
    }

    public String getDefaultRequiredMessage() {
        return defaultRequiredMessage;
    }

    public String combine(String... paths) {
        return String.join(".", paths);
    }

    @Autowired
    public void setDescriptionService(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    public DescriptionService getDescriptionService() {
        return descriptionService;
    }

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }
}

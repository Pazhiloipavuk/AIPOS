package lab3.validator;

import lab3.model.Constants;
import lab3.model.Description;
import lab3.model.Task;
import lab3.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@PropertySource("classpath:messages.properties")
public class DescriptionValidator extends AbstractValidator<Description> {

    private static final String TASK_NOT_FREE = "task.not.free";

    @Value("${task.not.free}")
    private String defaultTaskNotFreeMessage;

    public DescriptionValidator() {
        super(Description.class);
    }

    @Override
    public void validateTarget(Description description, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.DESCRIPTION_TITLE,
                REQUIRED_FIELD,
                getDefaultRequiredMessage());
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.DESCRIPTION_CONTENT,
                REQUIRED_FIELD,
                getDefaultRequiredMessage());
        validateTask(description, errors);
        isExistUser(description.getAuthor(), errors)
                .map(User::getId)
                .ifPresent(authorId -> isRightAuthor(authorId, errors));
    }

    private void validateTask(Description description, Errors errors) {
        isExistTask(description.getTask(), errors)
                .map(Task::getDescription)
                .map(Description::getId)
                .filter(Predicate.not(id -> description.getId().equals(id)))
                .ifPresent(id -> rejectTaskNotFreeError(errors));
    }

    private void rejectTaskNotFreeError(Errors errors) {
        errors.rejectValue(Constants.combine(Constants.DESCRIPTION_TASK, Constants.TASK_ID),
                TASK_NOT_FREE,
                defaultTaskNotFreeMessage);
    }
}


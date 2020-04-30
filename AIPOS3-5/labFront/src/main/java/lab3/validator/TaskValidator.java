package lab3.validator;

import lab3.model.Constants;
import lab3.model.Task;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class TaskValidator extends AbstractValidator<Task> {

    public TaskValidator() {
        super(Task.class);
    }

    @Override
    public void validateTarget(Task task, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                Constants.TASK_NAME, REQUIRED_FIELD,
                getDefaultRequiredMessage());
    }
}

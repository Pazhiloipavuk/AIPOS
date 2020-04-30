package lab3.validator;

import lab3.model.Constants;
import lab3.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import static lab3.model.Constants.USER_ID;

@Component
public class UserValidator extends AbstractValidator<User> {

    private static final String USER_NOT_VALID = "user.not.valid";

    @Value("${user.not.valid}")
    private String defaultUserNotValid;

    public UserValidator() {
        super(User.class);
    }

    @Override
    public void validateTarget(User user, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                Constants.USER_USERNAME, REQUIRED_FIELD,
                getDefaultRequiredMessage());
        getUserService().getCurrentUser()
                .map(user::equals)
                .filter(Boolean::booleanValue)
                .ifPresentOrElse(value -> {}, () -> errors.rejectValue(USER_ID, USER_NOT_VALID, defaultUserNotValid));
    }
}


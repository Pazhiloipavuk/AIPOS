package lab3.validator;

import lab3.model.Comment;
import lab3.model.Constants;
import lab3.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component
public class CommentValidator extends AbstractValidator<Comment> {

    public CommentValidator() {
        super(Comment.class);
    }

    @Override
    public void validateTarget(Comment comment, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, Constants.COMMENT_CONTENT,
                REQUIRED_FIELD,
                getDefaultRequiredMessage());
        isExistDescription(comment.getDescription(), errors);
        isExistUser(comment.getAuthor(), errors)
                .map(User::getId)
                .ifPresent(authorId -> isRightAuthor(authorId, errors));
    }
}

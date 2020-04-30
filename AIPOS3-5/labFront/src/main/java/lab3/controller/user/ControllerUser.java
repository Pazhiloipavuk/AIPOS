package lab3.controller.user;

import lab3.controller.ControllerConstants;
import lab3.model.User;
import lab3.service.UserService;
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
@RequestMapping(FULL_INFORMATION_ABOUT_USER_URL)
@NoArgsConstructor
@Slf4j
public class ControllerUser {

    private static final String USER_DETAILS_VIEW = "userDetails";
    private static final String MODEL_ATTRIBUTE = "user";

    private UserService userService;

    private AbstractValidator<User> validator;

    private ControllerAllUsers controllerAllUsers;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    public ControllerUser(UserService userService, AbstractValidator<User> validator,
                          ControllerAllUsers controllerAllUsers) {
        this.userService = userService;
        this.validator = validator;
        this.controllerAllUsers = controllerAllUsers;
    }

    @GetMapping
    public String findById(@PathVariable Long id, Model model) {
        log.trace("Find user by id = {}", id);
        userService.findById(id).ifPresent(model::addAttribute);
        return Optional.ofNullable(model.getAttribute(MODEL_ATTRIBUTE))
                .map(attribute -> USER_DETAILS_VIEW)
                .orElse(ControllerConstants.ERROR);
    }

    @PutMapping
    public String save(@Validated @ModelAttribute User user, BindingResult errors, Model model) {
        log.trace("Update user with id = {}", user.getId());
        if (errors.hasErrors()) {
            return USER_DETAILS_VIEW;
        } else {
            var updatedUser = userService.save(user);
            return findById(updatedUser.getId(), model);
        }
    }

    @DeleteMapping
    public String delete(@PathVariable Long id, Model model) {
        log.trace("Delete user with id = {}", id);
        userService.deleteById(id);
        return controllerAllUsers.findAll(model);
    }
}


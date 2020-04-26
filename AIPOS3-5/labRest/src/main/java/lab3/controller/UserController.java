package lab3.controller;

import lab3.model.User;
import lab3.service.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lab3.controller.ControllerConstants.USERS_URL;

@RestController
@NoArgsConstructor
@RequestMapping(USERS_URL)
public class UserController extends AbstractController<User> {

    private static final String USERNAME_URL = "/username/{username}";

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @GetMapping(USERNAME_URL)
    public User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username).orElse(null);
    }
}

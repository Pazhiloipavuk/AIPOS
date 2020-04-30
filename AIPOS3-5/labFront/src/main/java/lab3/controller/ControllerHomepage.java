package lab3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static lab3.controller.ControllerConstants.HOMEPAGE_URL;
import static lab3.controller.ControllerConstants.LOGIN_URL;

@Controller
@RequestMapping(HOMEPAGE_URL)
public class ControllerHomepage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHomepage.class.getName());

    private static final String HOMEPAGE_VIEW = "homepage";
    private static final String LOGIN_VIEW = "login";

    @GetMapping
    public String getHomepage() {
        LOGGER.trace("Homepage");
        return HOMEPAGE_VIEW;
    }

    @GetMapping(LOGIN_URL)
    public String getLogin() {
        LOGGER.trace("Login");
        return LOGIN_VIEW;
    }
}
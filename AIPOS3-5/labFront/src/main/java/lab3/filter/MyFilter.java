package lab3.filter;

import lab3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Component
public class MyFilter extends GenericFilterBean {

    private static final String CURRENT_USER_ATTRIBUTE = "currentUser";

    private UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        userService.getCurrentUser().ifPresent(user -> servletRequest.setAttribute(CURRENT_USER_ATTRIBUTE, user));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}


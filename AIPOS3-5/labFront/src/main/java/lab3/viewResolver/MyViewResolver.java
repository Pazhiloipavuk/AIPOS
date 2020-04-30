package lab3.viewResolver;

import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

@Component
public class MyViewResolver extends ContentNegotiatingViewResolver {

    private static final String PREFIX_DELIMITER = "/";
    private static final String FULLY_AUTHORIZED_USERS_PREFIX = "authorized";
    private static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getAuthorities)
                .filter(this::notAnonymous)
                .map(authentication -> resolveView(viewName, locale))
                .orElse(super.resolveViewName(viewName, locale));
    }

    private boolean notAnonymous(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .noneMatch(ROLE_ANONYMOUS::contains);
    }

    @SneakyThrows
    private View resolveView(String viewName, Locale locale) {
        return super.resolveViewName(String.join(PREFIX_DELIMITER, FULLY_AUTHORIZED_USERS_PREFIX, viewName), locale);
    }
}

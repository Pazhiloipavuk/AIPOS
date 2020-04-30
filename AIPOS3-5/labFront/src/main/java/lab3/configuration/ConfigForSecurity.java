package lab3.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ConfigForSecurity extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PAGE = "/login";

    private static final String ROOT_URL = "/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher(ROOT_URL)
                .authorizeRequests()
                .antMatchers(HttpMethod.PUT).authenticated()
                .antMatchers(HttpMethod.POST).authenticated()
                .antMatchers(HttpMethod.DELETE).authenticated()
                .antMatchers(HttpMethod.GET).permitAll()
                .and()
                .oauth2Login()
                .loginPage(LOGIN_PAGE)
                .and()
                .csrf().disable().cors().disable();
    }
}

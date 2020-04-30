package lab3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class ConfigForWeb {

    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    CharacterEncodingFilter encodingFilter() {
        return new CharacterEncodingFilter();
    }

    @Bean
    @Scope("prototype")
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

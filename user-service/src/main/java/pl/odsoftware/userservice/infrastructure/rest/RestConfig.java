package pl.odsoftware.userservice.infrastructure.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .errorHandler(new GithubApiErrorHandler())
                .build();
    }

    @Bean
    UserDetailsServerURL userDetailsProviderUrl(@Value("${user.details.url}") String value){
        return new UserDetailsServerURL(value);
    }

}

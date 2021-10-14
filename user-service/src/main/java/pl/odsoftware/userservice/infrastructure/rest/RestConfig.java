package pl.odsoftware.userservice.infrastructure.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class RestConfig {

    @Bean
    RestTemplate restTemplate(@Value("${rest.connection.timeout}") int connTimeout,
                              @Value("${rest.read.timeout}") int readTimeout) {
        return new RestTemplateBuilder()
                .errorHandler(new GithubApiErrorHandler())
                .setConnectTimeout(Duration.of(connTimeout, ChronoUnit.MILLIS))
                .setReadTimeout(Duration.of(readTimeout, ChronoUnit.MILLIS))
                .build();
    }

    @Bean
    UserDetailsServerURL userDetailsProviderUrl(@Value("${user.details.url}") String value){
        return new UserDetailsServerURL(value);
    }

}

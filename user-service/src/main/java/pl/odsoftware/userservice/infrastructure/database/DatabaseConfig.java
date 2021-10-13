package pl.odsoftware.userservice.infrastructure.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.odsoftware.userservice.application.LoginCounter;

@Configuration
public class DatabaseConfig {

    @Bean
    LoginCounter loginCounterRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new PostgresLoginCounterRepo(namedParameterJdbcTemplate);
    }

    @Bean
    PostgresHelperRepo postgresHelperRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new PostgresHelperRepo(namedParameterJdbcTemplate);
    }

}

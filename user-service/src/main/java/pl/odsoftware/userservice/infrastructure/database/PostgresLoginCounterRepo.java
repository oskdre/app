package pl.odsoftware.userservice.infrastructure.database;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.odsoftware.userservice.application.LoginCounter;

import java.util.Map;

public class PostgresLoginCounterRepo implements LoginCounter {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostgresLoginCounterRepo(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void incrementLoginSearchCounter(String login){
        int updated = jdbcTemplate.update("INSERT INTO request_details (login, request_count) " +
                        "VALUES (:login, 1) " +
                        "ON CONFLICT (login) DO UPDATE SET request_count = request_details.request_count + 1",
                Map.of("login", login));
        if(updated == 0){
            throw new LoginCounterIncrementException(String.format("No incrementation was done for login : %s", login));
        }
    }



}

package pl.odsoftware.userservice.infrastructure.database;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.util.Map;
import java.util.Optional;

public class PostgresHelperRepo {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostgresHelperRepo(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Integer> findCounterByLogin(String login){
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("select request_count from request_details " +
                            "where login = :login",
                    Map.of("login", login),
                    (rs, rowNum) -> rs.getInt("request_count"))
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void clearUpCounters(){
       jdbcTemplate.execute("TRUNCATE TABLE request_details", PreparedStatement::executeUpdate);
    }

}

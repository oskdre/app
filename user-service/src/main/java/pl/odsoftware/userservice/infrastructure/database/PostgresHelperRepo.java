package pl.odsoftware.userservice.infrastructure.database;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.PreparedStatement;
import java.util.Map;

public class PostgresHelperRepo {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PostgresHelperRepo(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int findCounterByLogin(String login){
        return jdbcTemplate.queryForObject("select request_count from request_details " +
                        "where login = :login",
                Map.of("login", login),
                (rs, rowNum) -> rs.getInt("request_count"));
    }

    public int clearUpCounters(){
        return jdbcTemplate.execute("TRUNCATE TABLE request_details", PreparedStatement::executeUpdate);
    }

}

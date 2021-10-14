package pl.odsoftware.userservice.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import pl.odsoftware.userservice.infrastructure.database.PostgresHelperRepo;
import pl.odsoftware.userservice.test.setup.BaseIntegration;
import pl.odsoftware.userservice.test.setup.GithubUserFactory;
import pl.odsoftware.userservice.web.model.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class RequestCounterIT extends BaseIntegration {

    @Autowired
    private FindUserDetails findUserDetails;
    @Autowired
    private PostgresHelperRepo database;

    @AfterEach
    void cleanUpEach(){
        database.clearUpCounters();
    }

    @Test
    @DisplayName("When github user is looked for once then their counter equals to one")
    void when_one_user_lookup_then_counter_equals_to_one(){
        //given
            setupGithubServer()
                    .withStatusCode(200)
                    .withResponseBody(GithubUserFactory.getUser("sample_user"));
        //when
            findUserDetails.findByLogin(Login.of("sample_user"));
        //then
            int userSearchingCounter = database.findCounterByLogin("sample_user").orElse(0);
            assertEquals(1, userSearchingCounter);
    }

    @Test
    @DisplayName("When github user is looked for twice then their counter equals to two")
    void when_one_user_lookup_twice_then_counter_equals_to_two(){
        //given
            setupGithubServer()
                    .withStatusCode(200)
                    .withResponseBody(GithubUserFactory.getUser("sample_user"));
        //when
        //first searching
            findUserDetails.findByLogin(Login.of("sample_user"));
        //second searching
            findUserDetails.findByLogin(Login.of("sample_user"));
        //then
            int userSearchingCounter = database.findCounterByLogin("sample_user").orElse(0);
            assertEquals(2, userSearchingCounter);
    }

    @Test
    @DisplayName("When github user was not looked up then their counter does not exist")
    void when_user_not_looked_up_then_counter_not_exist(){
        //when
            Integer found = database.findCounterByLogin("sample_user").orElse(0);
            assertEquals(0, found);
    }
}

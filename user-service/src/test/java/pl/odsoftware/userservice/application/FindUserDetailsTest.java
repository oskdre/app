package pl.odsoftware.userservice.application;

import io.vavr.Tuple2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.odsoftware.userservice.domain.Calculation;
import pl.odsoftware.userservice.domain.UserDetails;
import pl.odsoftware.userservice.test.setup.UserDetailsFactory;
import pl.odsoftware.userservice.web.model.Login;
import pl.odsoftware.userservice.web.model.UserDetailsResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindUserDetailsTest {

    private FindUserDetails findUserDetails;

    @Test
    @DisplayName("When user provider finds the user in external service then return user details with calculations")
    void when_user_found(){
        //given
            Tuple2<Calculation, UserDetails> regularUser = UserDetailsFactory.get("regular_user");
            findUserDetails = new FindUserDetails(login -> {}, (login -> Optional.of(regularUser._2)));
        //when
            Optional<UserDetailsResponse> result = findUserDetails.findByLogin(Login.of("test_login"));
        //then
            assertTrue(result.isPresent());
            assertEquals(String.valueOf(regularUser._1.value()), result.get().getCalculations());
            assertEquals(regularUser._2().getLogin(), result.get().getLogin());
            assertEquals(regularUser._2().getName(), result.get().getName());
            assertEquals(regularUser._2().getType(), result.get().getType());
    }

    @Test
    @DisplayName("When user provider cannot find the user in external service then return empty result")
    void when_user_not_found(){
        //given
            findUserDetails = new FindUserDetails(login -> {}, (login -> Optional.empty()));
        //when
            Optional<UserDetailsResponse> result = findUserDetails.findByLogin(Login.of("test_login"));
        //then
            assertTrue(result.isEmpty());
    }

}
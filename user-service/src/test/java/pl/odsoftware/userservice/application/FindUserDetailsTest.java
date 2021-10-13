package pl.odsoftware.userservice.application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.odsoftware.userservice.domain.UserDetails;
import pl.odsoftware.userservice.web.model.Login;
import pl.odsoftware.userservice.web.model.UserDetailsResponse;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FindUserDetailsTest {

    private FindUserDetails findUserDetails;

    @Test
    @DisplayName("When user provider cannot find the user on github then return empty result")
    void when_user_not_found(){
        //given
            findUserDetails = new FindUserDetails(login -> {}, (login -> Optional.empty()));
        //when
            Optional<UserDetailsResponse> result = findUserDetails.findByLogin(Login.of("test_login"));
        //then
            assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("When user provider finds the user on github then return user details with calculations")
    void when_user_found(){
        //given
            String expectedCalculationsResult = "8.0";
            UserDetails sampleUserDetails = new UserDetails(1,"test_login","test_name","test_type","test_avatar","test_date",3,2);
            findUserDetails = new FindUserDetails(login -> {}, (login -> Optional.of(sampleUserDetails)));
        //when
            Optional<UserDetailsResponse> result = findUserDetails.findByLogin(Login.of("test_login"));
        //then
            assertTrue(result.isPresent());
            assertEquals(expectedCalculationsResult, result.get().getCalculations());
    }

}
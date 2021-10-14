package pl.odsoftware.userservice.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDetailsTest {

    @ParameterizedTest(name = "{index} => followers={0}, publicRepos={1}, result={2}")
    @MethodSource("parametersAndExpectedResult")
    @DisplayName("When followers and public repos provided then do expected calculations")
    void when_parameters_provided_then_do_calculations(int followers, int publicRepos, double result){
        //given
            UserDetails userDetails = new UserDetails(1, "login", "name","type","url","createdAt", followers, publicRepos);
        //when
            UserDetails userDetailsResult = userDetails.doCalculations();
        //then
            assertEquals(result, userDetailsResult.getCalculations().value());
    }

    private static Stream<Arguments> parametersAndExpectedResult() {
        return Stream.of(
                Arguments.of(0, 5, 0.0),
                Arguments.of(3, 7, 18.0),
                Arguments.of(5, 0, 2.4),
                Arguments.of(0, 0, 0)
        );
    }



}
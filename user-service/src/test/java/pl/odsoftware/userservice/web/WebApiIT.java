package pl.odsoftware.userservice.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import pl.odsoftware.userservice.test.setup.BaseIntegration;
import pl.odsoftware.userservice.test.setup.GithubUserFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public class WebApiIT extends BaseIntegration {

    @LocalServerPort
    private Integer dynamicPort;

    private static final String SAMPLE_SEARCHED_LOGIN = "SampleName";
    private static final String SAMPLE_INVALID_LOGIN = "Sample_Name";

    @Test
    @DisplayName("When user is found on github then api returns expected response")
    void when_user_found_on_github_then_api_returns_according_response(){
        setupGithubServer()
                .withStatusCode(200)
                .withResponseBody(GithubUserFactory.getUser("sample_user"));

        given().port(dynamicPort)
                .when().get("/users/" + SAMPLE_SEARCHED_LOGIN)
                .then().statusCode(200)
                .body("id", equalTo("583231"))
                .body("login", equalTo("sample_login"))
                .body("name", equalTo("sample_name"))
                .body("type", equalTo("User"))
                .body("avatarUrl", equalTo("https://avatars.githubusercontent.com/u/583231?v=4"))
                .body("createdAt", equalTo("2011-01-25T18:44:36Z"))
                .body("calculations", equalTo("6.0"));
    }

    @Test
    @DisplayName("When user is not found on github then return response with 404 code")
    void when_user_not_found_on_github_then_api_returns_404(){
        setupGithubServer()
            .withStatusCode(404);

        given().port(dynamicPort)
                .when().get("/users/" + SAMPLE_SEARCHED_LOGIN)
                .then().statusCode(404);
    }

    @Test
    @DisplayName("When github responds with error then api returns 500")
    void when_github_error_then_api_returns_500(){
        setupGithubServer()
                .withStatusCode(450);

        given().port(dynamicPort)
                .when().get("/users/" + SAMPLE_SEARCHED_LOGIN)
                .then().statusCode(500);
    }

    @Test
    @DisplayName("When queried login is of incorrect form then api returns 400")
    void when_provided_login_is_invalid_then_api_returns_400(){
        setupGithubServer()
                .withStatusCode(200);

        given().port(dynamicPort)
                .when().get("/users/" + SAMPLE_INVALID_LOGIN)
                .then().statusCode(400);
    }

}

package pl.odsoftware.userservice.test.setup;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class BaseIntegration extends DatabaseContainerInitializer {

    private static WireMockServer wireMockServer = new WireMockServer(new WireMockConfiguration().port(5050));

    private int statusCode;

    protected BaseIntegration setupGithubServer() {
        wireMockServer.start();
        return this;
    }

    public BaseIntegration withStatusCode(int statusCode){
        this.statusCode = statusCode;
        wireMockServer.stubFor(get(urlMatching("/users/.*"))
                .willReturn(aResponse()
                        .withHeader("Connection","close")
                        .withStatus(this.statusCode)
                ));
        return this;
    }

    public void withResponseBody(String responseBody){
        wireMockServer.stubFor(get(urlMatching("/users/.*"))
                .willReturn(aResponse()
                        .withHeader("Connection","close")
                        .withHeader("Content-Type", "application/json")
                        .withStatus(this.statusCode)
                        .withBody(responseBody)
                ));
    }

    @AfterEach
    public void cleanup() {
        wireMockServer.stop();
    }

}

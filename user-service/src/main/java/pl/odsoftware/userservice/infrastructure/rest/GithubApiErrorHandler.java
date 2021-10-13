package pl.odsoftware.userservice.infrastructure.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Slf4j
public class GithubApiErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return !(response.getStatusCode().is2xxSuccessful() || response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        throw new UnexpectedServerResponse(String.format("Github response code : %s", response.getStatusCode()));
    }
}

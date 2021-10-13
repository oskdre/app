package pl.odsoftware.userservice.infrastructure.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.odsoftware.userservice.domain.UserDetails;
import pl.odsoftware.userservice.domain.UserDetailsProvider;

import java.util.Optional;

@Service
public class GithubApiClient implements UserDetailsProvider {

    private final RestTemplate restTemplate;
    private final UserDetailsServerURL serverURL;

    public GithubApiClient(RestTemplate restTemplate, UserDetailsServerURL userDetailsProviderUrl) {
        this.restTemplate = restTemplate;
        this.serverURL = userDetailsProviderUrl;
    }

    @Override
    public Optional<UserDetails> provide(String login) {
        HttpEntity<GithubUserDetails> request = new HttpEntity<>(new HttpHeaders());
        ResponseEntity<GithubUserDetails> githubResponse = restTemplate.exchange(buildTargetUrl(login), HttpMethod.GET, request, GithubUserDetails.class);
        if(userNotFound(githubResponse)){
            return Optional.empty();
        }
        GithubUserDetails githubUser = githubResponse.getBody();
        if(githubUser == null){
            throw new IllegalStateException(String.format("Github response for login %s is invalid", login));
        }
        return Optional.of(new UserDetails(
                githubUser.getId().orElseThrow(()-> new IllegalStateException(String.format("Github response for login %s missing id", login))),
                githubUser.getLogin(),
                githubUser.getName(),
                githubUser.getType(),
                githubUser.getAvatar_url(),
                githubUser.getCreated_at(),
                githubUser.getFollowers().orElseThrow(()-> new IllegalStateException(String.format("Github response for login %s missing followers", login))),
                githubUser.getPublic_repos().orElseThrow(()-> new IllegalStateException(String.format("Github response for login %s missing public repos", login)))
        ));
    }

    private boolean userNotFound(ResponseEntity<GithubUserDetails> githubResponse) {
        return githubResponse.getStatusCode().value() == 404;
    }

    private String buildTargetUrl(String login) {
        return serverURL.getValue() + login;
    }
}

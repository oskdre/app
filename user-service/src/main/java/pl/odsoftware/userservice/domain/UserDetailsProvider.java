package pl.odsoftware.userservice.domain;

import java.util.Optional;

public interface UserDetailsProvider {

    Optional<UserDetails> provide(String login);

}

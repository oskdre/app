package pl.odsoftware.userservice.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.odsoftware.userservice.domain.UserDetails;
import pl.odsoftware.userservice.domain.UserDetailsProvider;
import pl.odsoftware.userservice.web.model.Login;
import pl.odsoftware.userservice.web.model.UserDetailsResponse;

import java.util.Optional;


@Service @Slf4j
public class FindUserDetails {

    private final LoginCounter loginCounterRepo;
    private final UserDetailsProvider userDetailsProvider;

    public FindUserDetails(LoginCounter loginCounterRepo, UserDetailsProvider userDetailsProvider) {
        this.loginCounterRepo = loginCounterRepo;
        this.userDetailsProvider = userDetailsProvider;
    }

    public Optional<UserDetailsResponse> findByLogin(Login login){
        log.debug(String.format("Requested login : %s", login.value()));

        loginCounterRepo.incrementLoginSearchCounter(login.value());

       return userDetailsProvider.provide(login.value())
               .map(UserDetails::doCalculations)
               .map(UserDetailsResponse::from);
    }
}

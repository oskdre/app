package pl.odsoftware.userservice.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.odsoftware.userservice.application.FindUserDetails;
import pl.odsoftware.userservice.web.model.Login;
import pl.odsoftware.userservice.web.model.UserDetailsResponse;

import javax.validation.constraints.Pattern;

import static pl.odsoftware.userservice.web.validation.LoginValidation.GITHUB_LOGIN;


@RestController
@RequestMapping("/users")
@Validated
public class UserDetailsController {

    private final FindUserDetails findUserDetails;

    public UserDetailsController(FindUserDetails findUserDetails) {
        this.findUserDetails = findUserDetails;
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserDetailsResponse> getLogin(@PathVariable @Pattern(regexp = GITHUB_LOGIN) String login) {

       Login validatedLogin = Login.of(login);

       return findUserDetails.findByLogin(validatedLogin)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}

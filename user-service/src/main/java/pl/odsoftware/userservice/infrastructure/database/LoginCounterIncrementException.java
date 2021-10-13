package pl.odsoftware.userservice.infrastructure.database;

public class LoginCounterIncrementException extends RuntimeException {

    public LoginCounterIncrementException(String message) {
        super(message);
    }
}

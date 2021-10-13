package pl.odsoftware.userservice.infrastructure.rest;

public class UnexpectedServerResponse extends RuntimeException {

    public UnexpectedServerResponse(String message) {
        super(message);
    }
}

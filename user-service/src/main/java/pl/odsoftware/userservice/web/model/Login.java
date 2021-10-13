package pl.odsoftware.userservice.web.model;

public class Login {

    private final String value;

    public static Login of(String login){
        return new Login(login);
    }

    private Login(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}

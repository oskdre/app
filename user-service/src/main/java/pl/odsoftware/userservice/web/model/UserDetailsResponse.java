package pl.odsoftware.userservice.web.model;


import pl.odsoftware.userservice.domain.UserDetails;

public class UserDetailsResponse {

    private String id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private String calculations;

    public UserDetailsResponse(String id, String login, String name, String type, String avatarUrl, String createdAt, String calculations) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.calculations = calculations;
    }

    public UserDetailsResponse() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCalculations() {
        return calculations;
    }

    public void setCalculations(String calculations) {
        this.calculations = calculations;
    }

    public static UserDetailsResponse from(UserDetails userDetails){
        return new UserDetailsResponse(
                String.valueOf(userDetails.getId()),
                userDetails.getLogin(),
                userDetails.getName(),
                userDetails.getType(),
                userDetails.getAvatarUrl(),
                userDetails.getCreatedAt(),
                String.valueOf(userDetails.getCalculations().result())
        );
    }
}

package pl.odsoftware.userservice.domain;

import lombok.Getter;

@Getter
public class UserDetails {

    private final long id;
    private final String login;
    private final String name;
    private final String type;
    private final String avatarUrl;
    private final String createdAt;
    private final int followers;
    private final int publicRepos;
    private Calculation calculations;

    public UserDetails(long id, String login, String name, String type, String avatarUrl, String createdAt, int followers, int publicRepos) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
        this.followers = followers;
        this.publicRepos = publicRepos;
    }

    public UserDetails doCalculations(){
        this.calculations = new Calculation(followers, publicRepos);
        return this;
    }

}

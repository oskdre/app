package pl.odsoftware.userservice.domain;

import lombok.Getter;

@Getter
public class UserDetails {

    private long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private String createdAt;
    private int followers;
    private int publicRepos;
    private double calculations;

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
        if(noFollowers()){
            this.calculations = 0.0;
        } else {
            this.calculations = 6.0 / followers * (2.0 + publicRepos);
        }
        return this;
    }

    private boolean noFollowers() {
        return followers == 0;
    }
}

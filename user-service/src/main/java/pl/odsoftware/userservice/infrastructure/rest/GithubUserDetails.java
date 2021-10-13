package pl.odsoftware.userservice.infrastructure.rest;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class GithubUserDetails {

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatar_url;
    private String created_at;
    private Integer followers;
    private Integer public_repos;

    public Optional<Long> getId() {
        return Optional.ofNullable(id);
    }

    public void setId(long id) {
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Optional<Integer> getFollowers() {
        return Optional.ofNullable(followers);
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public Optional<Integer> getPublic_repos() {
        return Optional.ofNullable(public_repos);
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }
}

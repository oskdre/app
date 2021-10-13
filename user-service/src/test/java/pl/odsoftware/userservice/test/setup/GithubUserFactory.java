package pl.odsoftware.userservice.test.setup;

import java.util.HashMap;
import java.util.Map;

public class GithubUserFactory {

    private static final Map<String, String> USERS = new HashMap<>();

    static {
        USERS.put("sample_user", "{\"id\": 583231,\"login\":\"sample_login\",\"name\":\"sample_name\",\"type\":null,\"avatar_url\":\"https://avatars.githubusercontent.com/u/583231?v=4\",\"created_at\":\"2011-01-25T18:44:36Z\",\"type\": \"User\",\"followers\": 5,\"public_repos\":3}");
    }

    public static String getUser(String type){
        return USERS.get(type);
    }

}

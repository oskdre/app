package pl.odsoftware.userservice.test.setup;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import pl.odsoftware.userservice.domain.Calculation;
import pl.odsoftware.userservice.domain.UserDetails;

import java.util.HashMap;
import java.util.Map;

public class UserDetailsFactory {

    private static final Map<String, Tuple2<Calculation, UserDetails>> USERS = new HashMap<>();

    static {
        UserDetails userDetails1 = new UserDetails(1, "test_login", "test_name", "test_type", "test_avatar", "test_date", 3, 2);
        Calculation calculation1 = userDetails1.doCalculations().getCalculations();
        USERS.put("regular_user", Tuple.of(calculation1, userDetails1));
    }

    public static Tuple2<Calculation, UserDetails> get(String type){
        return USERS.get(type);
    }

}

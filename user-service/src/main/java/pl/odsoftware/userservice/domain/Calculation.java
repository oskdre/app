package pl.odsoftware.userservice.domain;

public class Calculation {

    private final double result;

    Calculation(int followers, int publicRepos) {
        this.result = execute(followers, publicRepos);
    }

    private double execute(int followers, int publicRepos){
        if(noFollowers(followers)){
            return 0.00;
        }
        return 6.0 / followers * (2.0 + publicRepos);
    }

    private boolean noFollowers(int followers) {
        return followers == 0;
    }

    public double value() {
        return result;
    }
}

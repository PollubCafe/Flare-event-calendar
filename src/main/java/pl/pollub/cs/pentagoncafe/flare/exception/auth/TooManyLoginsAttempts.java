package pl.pollub.cs.pentagoncafe.flare.exception.auth;

public class TooManyLoginsAttempts extends RuntimeException{
    public TooManyLoginsAttempts(){
        super("You tried to login more than 5 times. So your possibility of login is blocked for 15 minutes");
    }
}

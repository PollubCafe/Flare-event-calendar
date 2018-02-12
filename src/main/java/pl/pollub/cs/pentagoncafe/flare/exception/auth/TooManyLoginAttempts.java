package pl.pollub.cs.pentagoncafe.flare.exception.auth;

public class TooManyLoginAttempts extends RuntimeException{
    public TooManyLoginAttempts(){
        super("You tried to login more than 5 times. So your possibility of login is blocked for 15 minutes");
    }
}

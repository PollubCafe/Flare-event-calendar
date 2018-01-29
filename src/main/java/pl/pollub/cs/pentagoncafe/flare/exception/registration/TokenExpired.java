package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class TokenExpired extends RegistrationException{
    public TokenExpired(){
        super("Token expired. Please request for next activation email.");
    }
}

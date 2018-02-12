package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class TokenExpiredException extends RegistrationException{
    public TokenExpiredException(){
        super("Token expired. Please request for next activation email.");
    }
}

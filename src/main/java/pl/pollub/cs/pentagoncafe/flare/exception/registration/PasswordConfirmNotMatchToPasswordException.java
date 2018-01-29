package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class PasswordConfirmNotMatchToPasswordException extends RegistrationException{
    public PasswordConfirmNotMatchToPasswordException(){
        super("Password confirmation and password are not the same");
    }
}

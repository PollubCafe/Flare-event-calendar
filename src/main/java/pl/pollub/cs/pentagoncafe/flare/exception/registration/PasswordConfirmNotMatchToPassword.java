package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class PasswordConfirmNotMatchToPassword extends RegistrationException{
    public PasswordConfirmNotMatchToPassword(){
        super("Password confirmation and password are not the same");
    }
}

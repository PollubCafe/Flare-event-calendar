package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class UserAlreadyActivatedException extends RegistrationException{
    public UserAlreadyActivatedException(String nick){
        super("User: "+nick+" is already activated.");
    }
}

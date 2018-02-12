package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class UserWithThisEmailAlreadyExistException extends RegistrationException{
    public UserWithThisEmailAlreadyExistException(String email){
        super("User with e-mail: " + email + " already exist");
    }
}

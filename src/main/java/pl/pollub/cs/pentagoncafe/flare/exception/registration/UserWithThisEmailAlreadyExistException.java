package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class UserWithThisEmailAlreadyExistException extends RegistrationException{
    public UserWithThisEmailAlreadyExistException(String email){
        super(new StringBuilder("User with e-mail: ").append(email).append(" already exist").toString());
    }
}

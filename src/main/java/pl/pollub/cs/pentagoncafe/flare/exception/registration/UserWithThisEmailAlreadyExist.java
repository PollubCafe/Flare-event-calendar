package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class UserWithThisEmailAlreadyExist extends RegistrationException{
    public UserWithThisEmailAlreadyExist(String email){
        super(new StringBuilder("User with e-mail: ").append(email).append(" already exist").toString());
    }
}

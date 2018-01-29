package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class UserWithThisNickAlreadyExistException extends RegistrationException{
    public UserWithThisNickAlreadyExistException(String nick){
        super(new StringBuilder("User with nick: ").append(nick).append(" already exist").toString());
    }
}

package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class UserWithThisNickAlreadyExist extends RegistrationException{
    public UserWithThisNickAlreadyExist(String nick){
        super(new StringBuilder("User with nick: ").append(nick).append(" already exist").toString());
    }
}

package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class SendingVerificationMailFailedException extends RegistrationException{
    public SendingVerificationMailFailedException(){
        super("There are some problem with sending verification message. Please request for next verification message.");
    }
}

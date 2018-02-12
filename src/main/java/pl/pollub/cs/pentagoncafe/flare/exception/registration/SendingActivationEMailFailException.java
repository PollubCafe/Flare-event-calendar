package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class SendingActivationEMailFailException extends RegistrationException{
    public SendingActivationEMailFailException(){
        super("There are some problem with sending verification message. Please request for next verification message.");
    }
}

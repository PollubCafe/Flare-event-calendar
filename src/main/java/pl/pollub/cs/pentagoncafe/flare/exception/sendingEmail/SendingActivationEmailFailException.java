package pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail;

public class SendingActivationEmailFailException extends SendingEmailException {
    public SendingActivationEmailFailException(){
        super("There are some problem with sending verification message. Please request for next verification message.");
    }
}

package pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail;

public class SendingEmailException extends RuntimeException{
    SendingEmailException(String msg){
        super(msg);
    }
}

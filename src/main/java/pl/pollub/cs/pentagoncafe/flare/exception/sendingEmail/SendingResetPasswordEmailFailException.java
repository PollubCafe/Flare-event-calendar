package pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail;

public class SendingResetPasswordEmailFailException extends SendingEmailException{
    public SendingResetPasswordEmailFailException(){
        super("There are some problem with sending resetting password email. Please try again later or contact with administrator.");
    }
}

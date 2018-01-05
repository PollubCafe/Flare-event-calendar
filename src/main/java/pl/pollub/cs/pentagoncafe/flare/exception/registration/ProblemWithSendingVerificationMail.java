package pl.pollub.cs.pentagoncafe.flare.exception.registration;

public class ProblemWithSendingVerificationMail extends RegistrationException{
    public ProblemWithSendingVerificationMail(){
        super("There are some problem with sending verification message. Please request for next verification message.");
    }
}

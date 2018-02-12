package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.pollub.cs.pentagoncafe.flare.EventCalendarApplication;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.exception.ObjectNotFoundException;
import pl.pollub.cs.pentagoncafe.flare.exception.auth.TooManyLoginAttempts;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.RegistrationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(EventCalendarApplication.class);

    private final Messages messages;

    @Autowired
    public GlobalExceptionHandler(Messages messages) {
        this.messages = messages;
    }


    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<String> handleAccessDaniedException(Exception ex, WebRequest req) {

        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ObjectNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<String> handleNotFoundException(Exception ex, WebRequest req) {
        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {TooManyLoginAttempts.class, RegistrationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<String> handleBadRequest(Exception ex, WebRequest req) {
        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class, IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> handleInternalException(Exception ex, WebRequest req) {
        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));

        ex.printStackTrace();

        return new ResponseEntity<>(messages.get("unsolvableError"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

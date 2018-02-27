package pl.pollub.cs.pentagoncafe.flare.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import pl.pollub.cs.pentagoncafe.flare.exception.ResetPasswordException;
import pl.pollub.cs.pentagoncafe.flare.exception.auth.TooManyLoginAttempts;
import pl.pollub.cs.pentagoncafe.flare.exception.handler.error.ApiErrorsView;
import pl.pollub.cs.pentagoncafe.flare.exception.handler.error.ApiFieldError;
import pl.pollub.cs.pentagoncafe.flare.exception.registration.RegistrationException;
import pl.pollub.cs.pentagoncafe.flare.exception.sendingEmail.SendingEmailException;

import java.util.List;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(EventCalendarApplication.class);

    private final Messages messages;

    @Autowired
    public GlobalExceptionHandler(Messages messages) {
        this.messages = messages;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    )
    {
        BindingResult bindingResult = ex.getBindingResult();

        List<ApiFieldError> apiFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ApiFieldError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(toList());

        ApiErrorsView apiErrorsView = new ApiErrorsView(apiFieldErrors);

        return new ResponseEntity<>(apiErrorsView, HttpStatus.UNPROCESSABLE_ENTITY);
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

    @ExceptionHandler(value = {TooManyLoginAttempts.class, RegistrationException.class, ResetPasswordException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<String> handleBadRequest(Exception ex, WebRequest req) {
        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));
        ex.printStackTrace();
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {SendingEmailException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> handleInternalExceptionWithMessageForUser(Exception ex, WebRequest req) {
        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));

        ex.printStackTrace();

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RuntimeException.class, Exception.class, IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> handleInternalSecretException(Exception ex, WebRequest req) {
        logger.error(messages.get("exceptionMessage",ex.getClass().getSimpleName(),ex.getMessage()));

        ex.printStackTrace();

        return new ResponseEntity<>(messages.get("unsolvableError"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

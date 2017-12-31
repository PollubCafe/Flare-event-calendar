package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.pollub.cs.pentagoncafe.flare.EventCalendarApplication;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LogManager.getLogger(EventCalendarApplication.class);

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<String> handleInternalException(Exception ex, WebRequest req) {
        logger.error(
                new StringBuilder("exception: ")
                        .append(ex.getClass().getSimpleName())
                        .append(" message: ")
                        .append(ex.getMessage()).toString()
        );

        ex.printStackTrace();

        return new ResponseEntity<>(
                "There are unrecognized problems on the server side. Please contact with administrator.",
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

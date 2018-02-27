package pl.pollub.cs.pentagoncafe.flare.exception.handler.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiFieldError {
    private String field;
    private String message;
}

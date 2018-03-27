package pl.pollub.cs.pentagoncafe.flare.exception.handler.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiErrorsView {
    private List<ApiFieldError> fieldErrors;
}

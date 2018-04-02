package pl.pollub.cs.pentagoncafe.flare.DTO.request;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class EmailToEventAuthorReqDTO {
    @NotBlank
    @Size(max = 50, message = "validation.email.subject.size")
    private String subject;

    @NotBlank
    @Size(max = 1000, message = "validation.email.content.size")
    private String content;

    @NotBlank
    private String eventID;
}

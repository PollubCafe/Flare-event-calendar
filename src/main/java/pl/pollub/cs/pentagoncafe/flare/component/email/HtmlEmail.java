package pl.pollub.cs.pentagoncafe.flare.component.email;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class HtmlEmail extends Email{
    String htmlContent;

    public HtmlEmail(String from) {
        super(from);
    }
}

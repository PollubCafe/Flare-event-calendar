package pl.pollub.cs.pentagoncafe.flare.component.email.contentBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;

@Component
public class EmailContentBuilderImpl implements EmailContentBuilder {

    private final SpringTemplateEngine templateEngine;

    private final Messages messages;

    @Autowired
    public EmailContentBuilderImpl(SpringTemplateEngine templateEngine, Messages messages) {
        this.templateEngine = templateEngine;
        this.messages = messages;
    }

    public String buildActivationEmail(String activationToken, String recipientNick) {
        Context context = new Context();
        String activationURL = messages.get("registration.activationURL")+ activationToken;
        context.setVariable("activationURL", activationURL);
        context.setVariable("nick", recipientNick);
        return templateEngine.process("finishRegistration", context);
    }
}

package pl.pollub.cs.pentagoncafe.flare.component.email.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import pl.pollub.cs.pentagoncafe.flare.component.email.Email;
import pl.pollub.cs.pentagoncafe.flare.component.email.HtmlEmail;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;

@Component
public class EmailBuilderImpl implements EmailBuilder {

    private final SpringTemplateEngine templateEngine;

    private final Messages messages;

    private Email instance;

    @Autowired
    public EmailBuilderImpl(SpringTemplateEngine templateEngine, Messages messages) {
        this.templateEngine = templateEngine;
        this.messages = messages;
    }

    @Override
    public EmailBuilder buildActivationEmail(String activationToken) {
        HtmlEmail instance = new HtmlEmail("from@no-spam.com");
        instance.setSubject("Finish registration");

        Context emailTemplateCtx = new Context();
        String activationURL = messages.get("registration.activationURL")+ activationToken;
        emailTemplateCtx.setVariable("activationURL", activationURL);

        String templateName = "finishRegistration";
        String htmlContent = templateEngine.process(templateName, emailTemplateCtx);
        instance.setHtmlContent(htmlContent);

        this.instance = instance;

        return this;
    }

    @Override
    public EmailBuilder buildResetPasswordEmail(String generatedPassword) {
        HtmlEmail instance = new HtmlEmail("from@no-spam.com");
        instance.setSubject("Reset password");

        Context emailTemplateCtx = new Context();
        emailTemplateCtx.setVariable("generatedPassword", generatedPassword);

        String templateName = "forgotPassword";
        String htmlContent = templateEngine.process(templateName, emailTemplateCtx);
        instance.setHtmlContent(htmlContent);

        this.instance = instance;

        return this;
    }

    @Override
    public EmailBuilder buildEmailToEventAuthor(String messageContent, String subject) {
        HtmlEmail instance = new HtmlEmail("PentagonCafe");
        instance.setSubject(subject);

        Context emailTemplateContext = new Context();
        emailTemplateContext.setVariable("messageContent", messageContent);

        String templateName = "messageToEventAuthor";
        String htmlContent = templateEngine.process(templateName, emailTemplateContext);
        instance.setHtmlContent(htmlContent);

        this.instance = instance;

        return this;
    }

    @Override
    public Email to(String recipientAddress){
        instance.setTo(recipientAddress);

        return instance;
    }

}

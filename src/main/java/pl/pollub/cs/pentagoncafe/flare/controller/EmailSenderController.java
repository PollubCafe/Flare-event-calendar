package pl.pollub.cs.pentagoncafe.flare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailToEventAuthorReqDTO;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.service.email.EmailSenderService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/send")
public class EmailSenderController {
    private final EmailSenderService emailSenderService;

    private final Messages messages;

    @Autowired
    public EmailSenderController(EmailSenderService emailSenderService, Messages messages){
        this.emailSenderService = emailSenderService;
        this.messages = messages;
    }

    @PostMapping
    public ResponseEntity sendEmailToEventAuthor(@RequestBody @Valid @NotNull EmailToEventAuthorReqDTO emailToEventAuthorReqDTO){
        emailSenderService.sendEmail(emailToEventAuthorReqDTO);
        return ResponseEntity.ok(messages.get("email.successful"));
    }
}

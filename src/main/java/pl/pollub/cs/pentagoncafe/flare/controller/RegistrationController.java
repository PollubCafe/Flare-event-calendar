package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailReqDTO;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.component.validation.RegistrationReqDTOValidator;
import pl.pollub.cs.pentagoncafe.flare.service.registration.RegistrationService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final RegistrationReqDTOValidator registrationReqDTOValidator;
    private final Messages messages;

    @Autowired
    public RegistrationController(RegistrationService registrationService, RegistrationReqDTOValidator registrationReqDTOValidator, Messages messages) {
        this.registrationService = registrationService;
        this.registrationReqDTOValidator = registrationReqDTOValidator;
        this.messages = messages;
    }

    @InitBinder("registrationReqDTO")
    public void initValidators(WebDataBinder binder){
        binder.addValidators(registrationReqDTOValidator);
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody @Valid @NotNull RegistrationReqDTO registrationReqDTO) {
        registrationService.register(registrationReqDTO);
        return ResponseEntity.ok(messages.get("registration.successful"));
    }

    @PostMapping("/token")
    public void resendToken(@RequestBody @Valid @NotNull EmailReqDTO emailReqDTO){
        registrationService.resendToken(emailReqDTO);
    }

    @GetMapping("/confirm")
    public ResponseEntity finishRegistration(@RequestParam("token") String token){
        registrationService.finishRegistration(token);
        //return html page
        return ResponseEntity.ok(messages.get("registration.complete"));
    }
}

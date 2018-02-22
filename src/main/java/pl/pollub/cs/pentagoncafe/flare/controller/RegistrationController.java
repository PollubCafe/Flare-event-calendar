package pl.pollub.cs.pentagoncafe.flare.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.ResendTokenDTO;
import pl.pollub.cs.pentagoncafe.flare.component.message.Messages;
import pl.pollub.cs.pentagoncafe.flare.service.registration.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final Messages messages;

    @Autowired
    public RegistrationController(RegistrationService registrationService, Messages messages) {
        this.registrationService = registrationService;
        this.messages = messages;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody @NonNull RegistrationRequestDTO registrationRequestDTO) {
        registrationService.register(registrationRequestDTO);
    }

    @PostMapping(value = "/resendToken")
    @ResponseStatus(HttpStatus.OK)
    public void resendToken(@RequestBody @NonNull ResendTokenDTO resendTokenDTO){
        registrationService.resendToken(resendTokenDTO);
    }

    @GetMapping(value = "/confirm/{token}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity finishRegistration(@PathVariable("token") String token){
        registrationService.finishRegistration(token);
        //return html page
        return ResponseEntity.ok(messages.get("registration.complete"));
    }
}

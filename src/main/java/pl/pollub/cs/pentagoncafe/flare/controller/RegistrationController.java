package pl.pollub.cs.pentagoncafe.flare.controller;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.RegistrationRequestDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.ResendTokenDTO;
import pl.pollub.cs.pentagoncafe.flare.service.RegistrationService;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void registration(@RequestBody @NonNull RegistrationRequestDTO registrationRequestDTO) {
        registrationService.register(registrationRequestDTO);
    }

    @PostMapping(value = "/resendToken")
    @ResponseStatus(HttpStatus.OK)
    public void resendToken(@RequestBody ResendTokenDTO resendTokenDTO){
        registrationService.resendToken(resendTokenDTO);
    }

    @GetMapping(value = "/confirm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity confirmRegistration(@RequestParam("token") String token){
        registrationService.confirmRegistration(token);
        return ResponseEntity.ok("Registration completed. Please login to your account");
    }
}

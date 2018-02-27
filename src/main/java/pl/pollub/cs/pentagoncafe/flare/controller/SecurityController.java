package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.cs.pentagoncafe.flare.DTO.request.EmailReqDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.AuthUserResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.service.security.SecurityService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService){
        this.securityService = securityService;
    }

    @PostMapping("/api/forgotten_password")
    public void resetPassword(@RequestBody @Valid @NotNull EmailReqDTO emailReqDTO){
        securityService.resetPassword(emailReqDTO);
    }

    @GetMapping("/api/auth_user_data")
    public ResponseEntity<AuthUserResponseDTO> getAuthDataForLoggedUser(){
        return new ResponseEntity<>(securityService.getAuthDataForLoggedUser(), HttpStatus.OK);
    }
}

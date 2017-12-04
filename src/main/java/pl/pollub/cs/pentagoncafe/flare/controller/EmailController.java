package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.cs.pentagoncafe.flare.service.EmailService;

@RequestMapping("/api/email")
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping("/send.do")
    public void sendEmail() {
        emailService.send();
    }
}

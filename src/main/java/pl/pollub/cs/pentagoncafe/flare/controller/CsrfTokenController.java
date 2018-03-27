package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//this controller generate automatically new csrf token for session by sending empty html page
@Controller
public class CsrfTokenController {
    @GetMapping(value = "/api/csrf/index.html")
    public String secureBehindCsrfAttack(){
        return "csrf";
    }
}

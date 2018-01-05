package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/api/csrf")
public class CsrfSecurityController {

    @GetMapping(value = "/index.html")
    @ResponseBody
    public String secureBehindCsrfAttack(){
        return "index.html";
    }
}

package pl.pollub.cs.pentagoncafe.flare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CsrfSecurityController {

    @GetMapping(value = "/api/csrf/index.html")
    public String secureBehindCsrfAttack(){
        return "index";
    }
}

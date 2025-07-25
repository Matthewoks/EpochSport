package com.matthewoks.firstStep.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello/v1")
public class HelloController {
    @GetMapping("saluta")
    public String saluta(){
        return "Ciao, hai eseguito una chiamata.";
    }
}

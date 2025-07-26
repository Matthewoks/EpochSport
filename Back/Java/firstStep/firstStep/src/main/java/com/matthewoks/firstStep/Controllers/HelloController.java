package com.matthewoks.firstStep.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello/v1")
public class HelloController {
    //esempi da asilo

    @GetMapping("saluta")
    public String saluta(){
        return "Ciao, hai eseguito una chiamata.";
    }

    @GetMapping("numero")
    public Float dammiNumero(){
        return 11.9f;
    }

    @GetMapping("saluta/{varNome}")
    public String salutaPersona(@PathVariable String varNome){
        return "Ciao, "+varNome+" hai eseguito una chiamata.";
    }

    @GetMapping("saluta/{varNome}/{varCognome}")
    public String salutaPersona(@PathVariable String varNome, @PathVariable String varCognome){
        return "Ciao, "+varNome+" "+varCognome+" hai eseguito una chiamata.";
    }

    @GetMapping("salutaparam")
    public String salutaParametrizzato(
            @RequestParam(name="nome")  String varNome,
            @RequestParam(name="cognome") String varCognome,
            @RequestParam(name="eta") int varEta){
        return "Ciao, "+varNome+" "+varCognome+" "+varEta+" hai eseguito una chiamata.";
    }

    @PostMapping("saluta")
    public String salutaPost(){
        return "Ciao, hai eseguito una chiamata.";
    }
}

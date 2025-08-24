package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<User> getAll(){
        return repo.findAll(); //già disponibile senza fare nulla di particolare nella repo
    }

    @GetMapping("/username/{username}")
    public User getByUserName(@PathVariable String username){
        return repo.findByUsername(username); //già disponibile senza fare nulla di particolare nella repo
        //username è univoco solo un oggetto
    }

    @GetMapping("/language_preference/{lang}")
    public List<User> getByLanguagePreference(@PathVariable String lang){
        return repo.findByLanguagePreference(lang); //già disponibile senza fare nulla di particolare nella repo
    }

    @GetMapping("/fragmentusername/{username}")
    public List<User> getByFragmentUsername(@PathVariable String username){
        return repo.findByUsernameFragment(username); //già disponibile senza fare nulla di particolare nella repo
    }

    @GetMapping("/combined/{username}/{lang}")
    public User getByUsernameAndLanguagePreference(@PathVariable String username, @PathVariable String lang){

        return repo.findByUsernameAndLanguagePreference(username,lang); //già disponibile senza fare nulla di particolare nella repo
    }//ricerca combinata

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable int id){
        return repo.findById((long)id); //già disponibile senza fare nulla di particolare nella repo
        //se non utilizzo Optional return repo.findById((long)id).orElse(null);
    }

    @DeleteMapping("{varId}")
    public void delete(@PathVariable int varId) {
        if(varId!=0) repo.deleteById((long) varId);
    }

    @PostMapping
    public User save(@RequestBody User us){
        return repo.save(us); //salva e rende l'oggetto creato
    }
}

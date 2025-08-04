package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserRepository repo;

    @GetMapping
    public List<User> getAll(){
        return repo.findAll(); //già disponibile senza fare nulla di particolare nella repo
    }
}

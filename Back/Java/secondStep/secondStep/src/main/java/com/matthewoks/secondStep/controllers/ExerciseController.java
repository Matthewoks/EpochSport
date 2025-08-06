package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.models.Exercise;

import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.models.Workout;
import com.matthewoks.secondStep.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/exercises")
public class ExerciseController {

    @Autowired
    private ExerciseRepository repo;

    @GetMapping
    public List<Exercise> getAll(){
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Exercise> getById(@PathVariable int id){
        return repo.findById((long)id);
    }
    @PostMapping
    public Exercise save(@RequestBody Exercise ex) {
        return repo.save(ex);
    }
//    @GetMapping("{varId}")
//    public ResponseEntity exDetails(@PathVariable int varId){
//        Exercise ex = service.exDetailsService(varId);
//        if(ex!=null) return ResponseEntity.ok(ex);
//        else return ResponseEntity.notFound().build();
//
//    }
//
//    @GetMapping
//    public ResponseEntity exList(){
//        List<Exercise> exList = service.exListService();
//        if(exList!=null) return ResponseEntity.ok(exList);
//        else return ResponseEntity.notFound().build();
//
//    }
//
//    @PostMapping
//    public ResponseEntity exInsert(@RequestBody Exercise ex){
//        boolean insertResult = service.exInsertService(ex);
//        if(insertResult) return ResponseEntity.ok().build();
//        else return ResponseEntity.unprocessableEntity().build();
//    }
//
//    @DeleteMapping("{varId}")
//    public ResponseEntity exDelete(@PathVariable int varId){
//        boolean deleteResult = service.exDeleteService(varId);
//        if(deleteResult) return ResponseEntity.ok().build();
//        else return ResponseEntity.unprocessableEntity().build();
//    }
}

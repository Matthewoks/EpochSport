package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.dto.WorkoutDTO;
import com.matthewoks.secondStep.models.Exercise;
import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.models.Workout;
import com.matthewoks.secondStep.repositories.WorkoutRepository;
import com.matthewoks.secondStep.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutRepository repo; //secondo esempio di DI ostituisce l'immissione nel costruttore dentro controller e lo utilizza quando serve
    @Autowired
    private WorkoutService service;

//
//    @GetMapping
//    public List<Workout> getAll(){
//        return repo.findAll();
//    }
    @GetMapping("/{id}")
    public Optional<WorkoutDTO> getById(@PathVariable int id){
        return service.getWorkoutById((long)id);
    }
//    @GetMapping("/{id}")
//    public Optional<Workout> getById(@PathVariable int id){
//        return repo.findById((long)id);
//    }

    @PostMapping
    public Workout save(@RequestBody Workout wo) {
        return repo.save(wo);
    }


    @GetMapping
    public List<WorkoutDTO> getAllWorkouts() {
        return service.getAllWorkouts();
    }
//
//    @PostMapping
//    public ResponseEntity woInsert(@RequestBody Workout wo){
//      boolean insertResult = service.woInsertService(wo);
//      if(insertResult) return ResponseEntity.ok().build();
//      else return ResponseEntity.unprocessableEntity().build();
//    }
//
//    @DeleteMapping("{varId}")
//    public ResponseEntity woDelete(@PathVariable int varId){
//      boolean deleteResult = service.woDeleteService(varId);
//      if(deleteResult) return ResponseEntity.ok().build();
//      else return ResponseEntity.unprocessableEntity().build();
//    }
//
//
//
//
//////primo esempio di DI
////private final WorkoutService service;
////    //binContainer inietta solo se serve il service nel controller
////    public WorkoutController(WorkoutService service) {
////        this.service = service;
////    }
////    //per non stare a richiamare sempre in ogni metodo OggettoService ser = new OggettoSevice()
////    //dependency injection
}

package com.matthewoks.firstStep.Controllers;

import com.matthewoks.firstStep.Models.Workout;
import com.matthewoks.firstStep.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService service; //secondo esempio di DI ostituisce l'immissione nel costruttore dentro controller e lo utilizza quando serve

    @GetMapping("{varId}")
    public Workout restituisciWorkout(@PathVariable int varId){
        return service.getWorkoutById(varId);
//        Workout wo = new Workout(1,"Petto", "Blue", List.of("Panca","Barra"));
//        return wo;
    }

    @GetMapping()
    public List<Workout> restituisciElencoWorkout(){
        return service.getAllWorkouts();
//        List<Workout> elenco = new ArrayList<Workout>();
//        elenco.add(new Workout(1,"Petto", "Blue", List.of("Panca","Barra")));
//        elenco.add(new Workout(1,"Schiena", "Red", List.of("Letto","Divano")));
//        elenco.add(new Workout(1,"Gambe", "Violet", List.of("Squat","Pesi")));
//        return elenco;
    }

    @PostMapping("new")
    public Workout newWorkout(@RequestBody Workout wo){
      return service.addWorkout(wo);
    }




////primo esempio di DI
//private final WorkoutService service;
//    //binContainer inietta solo se serve il service nel controller
//    public WorkoutController(WorkoutService service) {
//        this.service = service;
//    }
//    //per non stare a richiamare sempre in ogni metodo OggettoService ser = new OggettoSevice()
//    //dependency injection
}

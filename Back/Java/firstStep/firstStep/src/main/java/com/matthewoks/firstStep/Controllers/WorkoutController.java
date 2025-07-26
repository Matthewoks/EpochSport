package com.matthewoks.firstStep.Controllers;

import com.matthewoks.firstStep.Models.Workout;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("workout")
public class WorkoutController {

    @GetMapping("detail")
    public Workout restituisciWorkout(){
        Workout wo = new Workout(1,"Petto", "Blue", List.of("Panca","Barra"));
        return wo;
    }

    @GetMapping("details")
    public List<Workout> restituisciElencoWorkout(){
        List<Workout> elenco = new ArrayList<Workout>();
        elenco.add(new Workout(1,"Petto", "Blue", List.of("Panca","Barra")));
        elenco.add(new Workout(1,"Schiena", "Red", List.of("Letto","Divano")));
        elenco.add(new Workout(1,"Gambe", "Violet", List.of("Squat","Pesi")));

        return elenco;
    }

//    @GetMapping("detail")
//    public Workout restituisciWorkout(
//        @RequestParam(name="id")  int varId){
//
//        //to do ricerca del Workout
//        Workout wo = new Workout(1,"Petto", "Blue", List.of("Panca","Barra"));
//        return wo;
//    }

    @PostMapping("new")
    public Workout newWorkout(@RequestBody Workout wo){
      return wo;
    }
}

package com.matthewoks.firstStep.Services;

import com.matthewoks.firstStep.Models.Workout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {

    private List<Workout> workouts = new ArrayList<Workout>() {
        {
            add(new Workout(1, "Petto", "Blue", List.of("Panca", "Barra")));
            add(new Workout(2, "Schiena", "Red", List.of("Letto", "Divano")));
            add(new Workout(3, "Gambe", "Violet", List.of("Squat", "Pesi")));


        }
    };

    //restituisce tutto
    public List<Workout> getAllWorkouts(){
        return workouts;
    }

    //restituisce un solo
    public Workout getWorkoutById(int id){
        return workouts.stream().filter(p->p.getId()==id).findFirst().orElse(null);
    }

    //inserisci un elemento
    public Workout addWorkout(Workout wo){
        wo.setId(workouts.size()+1);
        workouts.add(wo);
        return wo;
    }
}

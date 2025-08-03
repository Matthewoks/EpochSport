package com.matthewoks.firstStep.Services;

import com.matthewoks.firstStep.Models.Workout;
import com.matthewoks.firstStep.Repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository repo;

   public List<Workout> woListService(){
       return repo.getAll();
   }

    private List<Workout> workouts = new ArrayList<Workout>() {
        {
//            add(new Workout(1, "Petto", "Blue"));
//            add(new Workout(2, "Schiena", "Red"));
//            add(new Workout(3, "Gambe", "Violet"));


        }
    };

    //restituisce tutto
    public List<Workout> getAllWorkouts(){
        return workouts;
    }

    //restituisce un solo
    public Workout woDetailsService(int id){
       return repo.getById(id);
        // return workouts.stream().filter(p->p.getId()==id).findFirst().orElse(null);
    }

    //inserisci un elemento
    public boolean woInsertService(Workout wo){
        return repo.Insert(wo);
//        wo.setId(workouts.size()+1);
//        workouts.add(wo);
//        return wo;
    }

    public boolean woDeleteService(int id){
        return repo.Delete(id);
    }
}

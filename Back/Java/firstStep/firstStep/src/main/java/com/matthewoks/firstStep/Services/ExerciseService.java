package com.matthewoks.firstStep.Services;

import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Exercise;
import com.matthewoks.firstStep.Models.Workout;
import com.matthewoks.firstStep.Repositories.ExerciseRepository;
import com.matthewoks.firstStep.Repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    private ExerciseRepository repo;

    public List<Exercise> exListService(){
        return repo.getAll();
    }
    //restituisce un solo
    public Exercise exDetailsService(int id){
        return repo.getById(id);
        // return workouts.stream().filter(p->p.getId()==id).findFirst().orElse(null);
    }

    public boolean exInsertService(Exercise ex){
        return repo.Insert(ex);

    }
}

package com.matthewoks.firstStep.Services;

import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Exercise;
import com.matthewoks.firstStep.Models.Workout;
import com.matthewoks.firstStep.Repositories.EquipmentRepository;
import com.matthewoks.firstStep.Repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository repo;

    public List<Equipment> eqListService(){
        return repo.getAll();
    }

    //restituisce un solo
    public Equipment eqDetailsService(long id){
        return repo.getById(id);
        // return workouts.stream().filter(p->p.getId()==id).findFirst().orElse(null);
    }
    public boolean eqInsertService(Equipment eq){
        return repo.Insert(eq);
    }
    public boolean eqUpdateService(Equipment eq){
        return repo.Update(eq);
    }
    public boolean eqDeleteService(int id){
        return repo.Delete(id);
    }
}

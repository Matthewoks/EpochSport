package com.matthewoks.firstStep.Repositories;

import com.matthewoks.firstStep.Models.Workout;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkoutRepository implements IRepositoryRead<Workout>,IRepositoryWrite<Workout> {
    @Override
    public Workout getById(int id) {
        return null;
    }
    public Workout getByName(String name) {
        return null;
    }
    @Override
    public List<Workout> getAll() {
        return List.of();
    }

    @Override
    public boolean Insert(Workout obj) {
        return false;
    }

    @Override
    public boolean Update(Workout obj) {
        return false;
    }

    @Override
    public boolean Delete(Workout obj) {
        return false;
    }


    //CRUD

    //CREATE

    //READ

    //UPDATE

    //DELETE
}

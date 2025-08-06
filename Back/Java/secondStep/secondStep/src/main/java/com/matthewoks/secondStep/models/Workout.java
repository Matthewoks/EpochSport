package com.matthewoks.secondStep.models;

import java.util.List;

public class Workout extends Meet {


   private List<Exercise> exercises;


    public Workout() {
        super();
    }

    public Workout(Long id, String meetName, String color, List<Exercise> exercises) {
        super(id,meetName,color);
        this.exercises=exercises;


    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return "Workout{ } " + super.toString();
    }
}

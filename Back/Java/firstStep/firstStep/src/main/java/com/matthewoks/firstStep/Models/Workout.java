package com.matthewoks.firstStep.Models;

import java.util.List;

public class Workout extends Meet {


   private List<Exercise> exercises;


    public Workout() {
        super();
    }

    public Workout(int id, String meetName, String color, List<Exercise> exercises) {
        super(id,meetName,color);
        this.exercises=exercises;


    }





    @Override
    public String toString() {
        return "Workout{ } " + super.toString();
    }
}

package com.matthewoks.firstStep.Models;

public class Workout extends Meet {





    public Workout() {
        super();
    }

    public Workout(int id, String meetName, String color) {
        super(id,meetName,color);



    }





    @Override
    public String toString() {
        return "Workout{ } " + super.toString();
    }
}

package com.matthewoks.firstStep.Models;

import java.util.List;

public class Workout extends Meet {


    private String Color;
    private List<String> Exercises;

    public Workout() {
        super();
    }

    public Workout(int id, String meetName, String color, List<String> exercises) {
        super(id, meetName);
        Color = color;
        Exercises = exercises;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public List<String> getExercises() {
        return Exercises;
    }

    public void setExercises(List<String> exercises) {
        Exercises = exercises;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "Color='" + Color + '\'' +
                ", Exercises=" + Exercises +
                "} " + super.toString();
    }
}

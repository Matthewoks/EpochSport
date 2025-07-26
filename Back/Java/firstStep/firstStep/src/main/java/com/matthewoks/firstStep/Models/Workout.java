package com.matthewoks.firstStep.Models;

import java.util.List;

public class Workout {
    private int Id;
    private String Name;
    private String Color;
    private List<String> Exercises;

    public Workout(int id, String name, String color, List<String> exercises) {
        Id = id;
        Name = name;
        Color = color;
        Exercises = exercises;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Color='" + Color + '\'' +
                ", Exercises=" + Exercises +
                '}';
    }
}

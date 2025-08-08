package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="workouts")
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor
public class Workout extends Meet {

    @ManyToMany
    @JoinTable(
            name = "workouts_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exercises = new ArrayList<>();
   //private Set<Exercise> exercises;


    public Workout(Long id, String name, String color, List<Exercise> exercises) {
        super(id,name,color);
        this.exercises=exercises;
    }
}

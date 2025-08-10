package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="exercises")
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor
public class Exercise extends PlannedAction {
    private int duration;
    private int repetitions;
    private int sets;
    @Column(name="rest_time")
    private int restTime;
    @Column(name="execution_mode")
    private String executionMode;
//    @ManyToMany(mappedBy = "exercises")
//    @JoinTable(
//            name = "exercises_equipments",
//            joinColumns = @JoinColumn(name = "exercise_id"),
//            inverseJoinColumns = @JoinColumn(name = "equipment_id")
//    )
//    private Set<Equipment> equipments;
    @Column(name="intensity_level")
    private int intensityLevel;
//    @ManyToMany(mappedBy = "workoutExercises")
//    private List<Workout> workouts = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "exercises_equipments",
//            joinColumns = @JoinColumn(name = "exercise_id"),
//            inverseJoinColumns = @JoinColumn(name = "equipment_id")
//    )
//    private List<Equipment> equipments = new ArrayList<>();

    // Collegamento alla join table con equipments
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseEquipment> exerciseEquipments = new ArrayList<>();


    public Exercise(Long id, String name, String description, String color, String imageUrl, int duration, int repetitions, int sets, int restTime, String executionMode, int intensityLevel, List<ExerciseEquipment> exerciseEquipments) {
        super(id, name, description, color, imageUrl);
        this.duration = duration;
        this.repetitions = repetitions;
        this.sets = sets;
        this.restTime = restTime;
        this.executionMode = executionMode;
        this.intensityLevel = intensityLevel;
//        this.workouts = workouts;
        this.exerciseEquipments = exerciseEquipments;
    }


}

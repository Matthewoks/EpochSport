package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="Equipments")
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
    @ManyToMany(mappedBy = "exercises")
    @JoinTable(
            name = "exercises_equipments",
            joinColumns = @JoinColumn(name = "exercise_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments;
    @Column(name="intensity_level")
    private int intensityLevel;



    public Exercise(Long id, String name, String description, String color, String imageUrl, int duration, int repetitions, int sets, int restTime, String executionMode, Set<Equipment> equipments, int intensityLevel) {
        super(id, name, description, color, imageUrl);
        this.duration = duration;
        this.repetitions = repetitions;
        this.sets = sets;
        this.restTime = restTime;
        this.executionMode = executionMode;
        this.equipments = equipments;
        this.intensityLevel = intensityLevel;
    }


}

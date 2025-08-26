package com.matthewoks.secondStep.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {
    private Long id;
    private String name;
    private String description;
    private String color;
    private int duration;
    private int repetitions;
    private int sets;
    private int restTime;
    private String executionMode;
    private int intensityLevel;
    private List<EquipmentDTO> equipments;
}



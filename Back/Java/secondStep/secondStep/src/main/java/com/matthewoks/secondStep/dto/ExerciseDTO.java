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
    private List<EquipmentDTO> equipments;
}



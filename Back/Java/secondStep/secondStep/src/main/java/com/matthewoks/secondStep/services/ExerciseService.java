package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.dto.EquipmentDTO;
import com.matthewoks.secondStep.dto.ExerciseDTO;
import com.matthewoks.secondStep.models.Exercise;
import com.matthewoks.secondStep.models.ExerciseEquipment;
import com.matthewoks.secondStep.repositories.EquipmentRepository;
import com.matthewoks.secondStep.repositories.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    @Autowired
    private ExerciseRepository repo ;

    private ExerciseDTO mapToDTO(Exercise exercise) {
        ExerciseDTO dto = new ExerciseDTO();
        dto.setId(exercise.getId());
        dto.setName(exercise.getName());
        dto.setEquipments(
                exercise.getExerciseEquipments().stream()
                        .map(eq -> new EquipmentDTO(
                                eq.getEquipment().getId(),
                                eq.getEquipment().getName()))
                        .toList()
        );
        return dto;
    }


    public Optional<ExerciseDTO> getExerciseById(Long id) {
        return repo.findById(id)
                .map(this::mapToDTO);
    }

    public List<ExerciseDTO> getAllExercises() {
        return repo.findAll().stream().map(ex -> {
            ExerciseDTO dto = new ExerciseDTO();
            dto.setId(ex.getId());
            dto.setName(ex.getName());
            dto.setEquipments(
                    ex.getExerciseEquipments().stream()
                            .map(eq -> new EquipmentDTO(eq.getEquipment().getId(), eq.getEquipment().getName()))
                            .collect(Collectors.toList())
            );
            return dto;
        }).collect(Collectors.toList());
    }
}

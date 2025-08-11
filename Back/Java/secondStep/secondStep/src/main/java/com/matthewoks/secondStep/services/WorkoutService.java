package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.dto.EquipmentDTO;
import com.matthewoks.secondStep.dto.ExerciseDTO;
import com.matthewoks.secondStep.dto.WorkoutDTO;
import com.matthewoks.secondStep.models.Exercise;
import com.matthewoks.secondStep.models.Workout;
import com.matthewoks.secondStep.repositories.ExerciseRepository;
import com.matthewoks.secondStep.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkoutService {
    @Autowired
    private WorkoutRepository repo ;

    private WorkoutDTO mapToDTO(Workout workout) {
        WorkoutDTO dto = new WorkoutDTO();
        dto.setId(workout.getId());
        dto.setName(workout.getName());
        dto.setExercises(
                workout.getWorkoutExercises().stream()
                        .map(ex -> new ExerciseDTO(
                                ex.getExercise().getId(),
                                ex.getExercise().getName(),
                                ex.getExercise().getExerciseEquipments().stream()
                                        .map(eq -> new EquipmentDTO(
                                                eq.getEquipment().getId(),
                                                eq.getEquipment().getName()
                                        ))
                                        .toList()
                        ))
                        .toList()
        );
        return dto;
    }


    public List<WorkoutDTO> getAllWorkouts() {
        return repo.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public Optional<WorkoutDTO> getWorkoutById(Long id) {
        return repo.findById(id)
                .map(this::mapToDTO);
    }


}

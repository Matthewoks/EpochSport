package com.matthewoks.secondStep.controllers;


import com.matthewoks.secondStep.models.WorkoutExercise;
import com.matthewoks.secondStep.services.WorkoutExerciseService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workout-exercises")
@RequiredArgsConstructor
public class WorkoutExerciseController {

    @Autowired
    private WorkoutExerciseService workoutExerciseService;

    @PostMapping
    public WorkoutExercise create(@RequestBody WorkoutExerciseRequest request) {
        return workoutExerciseService.addWorkoutExercise(request.getWorkoutId(), request.getExerciseId());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        workoutExerciseService.deleteWorkoutExercise(id);
    }

    // DTO per ricevere solo gli ID
    @Data
    public static class WorkoutExerciseRequest {
        private Long workoutId;
        private Long exerciseId;
    }
}

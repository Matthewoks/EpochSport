package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.models.Exercise;
import com.matthewoks.secondStep.models.Workout;
import com.matthewoks.secondStep.models.WorkoutExercise;
import com.matthewoks.secondStep.repositories.ExerciseRepository;
import com.matthewoks.secondStep.repositories.WorkoutExerciseRepository;
import com.matthewoks.secondStep.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class WorkoutExerciseService {
    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;



    @Transactional
    public WorkoutExercise addWorkoutExercise(Long workoutId, Long exerciseId) {
        if (workoutExerciseRepository.existsByWorkoutIdAndExerciseId(workoutId, exerciseId)) {
            throw new IllegalArgumentException("WorkoutExercise già presente");
        }

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new IllegalArgumentException("Workout non trovato"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise non trovato"));

        WorkoutExercise workoutExercise = new WorkoutExercise();
        workoutExercise.setWorkout(workout);
        workoutExercise.setExercise(exercise);

        return workoutExerciseRepository.save(workoutExercise);
    }

    public void deleteWorkoutExercise(Long id) {
        workoutExerciseRepository.deleteById(id);
    }
}







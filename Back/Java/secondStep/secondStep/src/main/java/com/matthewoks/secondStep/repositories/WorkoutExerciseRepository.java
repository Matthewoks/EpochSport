package com.matthewoks.secondStep.repositories;

import com.matthewoks.secondStep.models.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {

    boolean existsByWorkoutIdAndExerciseId(Long workoutId, Long exerciseId);
}

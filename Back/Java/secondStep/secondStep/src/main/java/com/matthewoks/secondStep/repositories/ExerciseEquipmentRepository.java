package com.matthewoks.secondStep.repositories;

import com.matthewoks.secondStep.models.ExerciseEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseEquipmentRepository extends JpaRepository<ExerciseEquipment, Long> {
    boolean existsByExerciseIdAndEquipmentId(Long exerciseId, Long equipmentId);
}


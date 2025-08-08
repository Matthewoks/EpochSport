package com.matthewoks.secondStep.repositories;

import com.matthewoks.secondStep.models.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // Schedules in un range di date
    List<Plan> findByScheduledDateBetween(LocalDate start, LocalDate end);

    // Controllo duplicati
    @Query("SELECT s FROM Plan s WHERE s.workout.id = :workoutId AND s.user.id = :userId AND s.scheduledDate = :scheduledDate")
    Plan findDuplicate(Long workoutId, Long userId, LocalDate scheduledDate);
}

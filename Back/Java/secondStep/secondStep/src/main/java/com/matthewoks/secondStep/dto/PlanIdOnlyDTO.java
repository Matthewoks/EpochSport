package com.matthewoks.secondStep.dto;

import com.matthewoks.secondStep.models.Plan;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlanIdOnlyDTO {
    private Long id;
    private Long workoutId;
    private Long userId;
    private LocalDate scheduledDate;

    public static PlanIdOnlyDTO fromEntity(Plan s) {
        return new PlanIdOnlyDTO(
                s.getId(),
                s.getWorkout().getId(),
                s.getUser().getId(),
                s.getScheduledDate()
        );
    }
}

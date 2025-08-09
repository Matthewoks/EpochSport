package com.matthewoks.secondStep.dto;


import com.matthewoks.secondStep.models.Plan;
import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.models.Workout;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PlanFullDTO {
    private Long id;
    private Workout workout;
    private User user;
    private LocalDate scheduledDate;

    public static PlanFullDTO fromEntity(Plan s) {
        return new PlanFullDTO(
                s.getId(),
                s.getWorkout(),
                s.getUser(),
                s.getScheduledDate()
        );
    }
}

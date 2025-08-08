package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "plans",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"workout_id", "user_id", "scheduled_date"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Collegamento a Workout
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    // Collegamento a User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Data della schedulazione
    @Column(name = "scheduled_date", nullable = false)
    private LocalDate scheduledDate;
}

package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.models.Plan;
import com.matthewoks.secondStep.repositories.PlanRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    @Autowired
    private final PlanRepository planRepository;

    // Tutti in un range di date
    public List<Plan> getPlansInRange(LocalDate start, LocalDate end) {
        return planRepository.findByScheduledDateBetween(start, end);
    }

    // Uno solo per ID
    public Plan getPlanById(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plan non trovata con ID: " + id));
    }

    // Aggiungere una singola
    public Plan addPlan(Plan plan) {
        if (planRepository.findDuplicate(plan.getWorkout().getId(), plan.getUser().getId(), plan.getScheduledDate()) != null) {
            throw new IllegalArgumentException("Questa Plan esiste già");
        }
        return planRepository.save(plan);
    }

    // Aggiungere una singola Plan alla volta
//    public List<Plan> addPlans(List<Plan> plans) {
//        List<Plan> savedPlans = new ArrayList<>();
//
//        for (Plan plan : plans) {
//            boolean exists = planRepository.findDuplicate(
//                    plan.getWorkout().getId(),
//                    plan.getUser().getId(),
//                    plan.getScheduledDate()
//            ) != null;
//
//            if (!exists) {
//                savedPlans.add(planRepository.save(plan));
//            } else {
//                // duplicata non faccio nulla
//            }
//        }
//        return savedPlans;
//    }

    // Aggiungere più Plan
    public List<Plan> addPlans(List<Plan> Plans) {
        return Plans.stream().map(this::addPlan).toList();

    }

    // Cancellarne una
    public void deletePlan(Long id) {
        if (!planRepository.existsById(id)) {
            throw new EntityNotFoundException("Plan non trovata con ID: " + id);
        }
        planRepository.deleteById(id);
    }

    // Cancellarne più
    public void deletePlans(List<Long> ids) {
        ids.forEach(this::deletePlan);
    }

    // Aggiornamento parziale (PATCH)
    public Plan updatePartial(Long id, Plan updated) {
        Plan existing = getPlanById(id);

        if (updated.getWorkout() != null) {
            existing.setWorkout(updated.getWorkout());
        }
        if (updated.getUser() != null) {
            existing.setUser(updated.getUser());
        }
        if (updated.getScheduledDate() != null) {
            existing.setScheduledDate(updated.getScheduledDate());
        }

        return planRepository.save(existing);
    }
}

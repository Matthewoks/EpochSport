package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.dto.PlanFullDTO;
import com.matthewoks.secondStep.dto.PlanIdOnlyDTO;
import com.matthewoks.secondStep.models.Plan;
import com.matthewoks.secondStep.services.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    // 🔹 GET: tutte in range
//    @GetMapping
//    public List<Plan> getPlansInRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
//        return planService.getPlansInRange(start, end);
//    }
    @GetMapping
    public List<?> getPlansInRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(defaultValue = "false") boolean details) {

        List<Plan> schedules = planService.getPlansInRange(start, end);

        if (details) {
            return schedules.stream()
                    .map(PlanFullDTO::fromEntity)
                    .toList();
        } else {
            return schedules.stream()
                    .map(PlanIdOnlyDTO::fromEntity)
                    .toList();
        }
    }
    // 🔹 GET: una sola
    @GetMapping("/{id}")
    public Plan getPlanById(@PathVariable Long id) {
        return planService.getPlanById((Long)id);
    }

    // 🔹 POST: una o più
    @PostMapping
    public List<Plan> addPlans(@RequestBody List<Plan> Plans) {
        return planService.addPlans(Plans);
    }

    // 🔹 DELETE: una o più
    @DeleteMapping
    public ResponseEntity<Void> deletePlans(@RequestBody List<Long> ids) {
        planService.deletePlans(ids);
        return ResponseEntity.noContent().build();
    }

    // 🔹 PATCH: aggiornamento parziale
    @PatchMapping("/{id}")
    public Plan updatePartial(@PathVariable Long id, @RequestBody Plan updated) {
        return planService.updatePartial(id, updated);
    }
}

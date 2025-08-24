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

    private final PlanService service;

//    @GetMapping
//    public List<Plan> getPlansInRange(
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
//            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
//        return service.getPlansInRange(start, end);
//    }
    @GetMapping
    public List<?> getPlansInRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(defaultValue = "false") boolean details) {

        List<Plan> schedules = service.getPlansInRange(start, end);

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

    @GetMapping("/{id}")
    public Plan getPlanById(@PathVariable Long id) {
        return service.getPlanById((Long)id);
    }


    @PostMapping
    public List<Plan> addPlans(@RequestBody List<Plan> Plans) {
        return service.addPlans(Plans);
    }


    @DeleteMapping
    public ResponseEntity<Void> deletePlans(@RequestBody List<Long> ids) {
        service.deletePlans(ids);
        return ResponseEntity.noContent().build();
    }


    //PATCH: aggiornamento parziale
    @PatchMapping("/{id}")
    public Plan updatePartial(@PathVariable Long id, @RequestBody Plan updated) {
        return service.updatePartial(id, updated);
    }
}

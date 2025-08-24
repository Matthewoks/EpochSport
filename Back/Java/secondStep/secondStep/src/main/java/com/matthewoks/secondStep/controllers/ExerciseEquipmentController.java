package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.dto.ExerciseEquipmentDTO;
import com.matthewoks.secondStep.dto.ExerciseEquipmentResponseDTO;
import com.matthewoks.secondStep.models.ExerciseEquipment;
import com.matthewoks.secondStep.services.ExerciseEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ ")
public class ExerciseEquipmentController {

    @Autowired
    private ExerciseEquipmentService service;

    @GetMapping("/{id}")
    public ExerciseEquipment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ExerciseEquipment> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ResponseEntity<ExerciseEquipment> create(@RequestBody ExerciseEquipmentDTO dto) {
        ExerciseEquipment saved = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }



    @PostMapping("/multiple")
    public List<ExerciseEquipmentResponseDTO> addMultiple(@RequestBody List<ExerciseEquipmentDTO> list) {
        return service.addMultiple(list);
    }
    @PostMapping("/solo")
    public ExerciseEquipment add(@RequestBody ExerciseEquipment ee) {
        return service.addExerciseEquipment(ee);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}


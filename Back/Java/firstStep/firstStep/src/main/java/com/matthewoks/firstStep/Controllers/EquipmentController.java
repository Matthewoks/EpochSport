package com.matthewoks.firstStep.Controllers;

import com.matthewoks.firstStep.Models.Equipment;
import com.matthewoks.firstStep.Models.Workout;
import com.matthewoks.firstStep.Services.EquipmentService;
import com.matthewoks.firstStep.Services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentService service;

    @GetMapping("{varId}")
    public ResponseEntity eqDetails(@PathVariable int varId){
        Equipment eq = service.eqDetailsService(varId);
        if(eq!=null) return ResponseEntity.ok(eq);
        else return ResponseEntity.notFound().build();

    }

    @GetMapping
    public ResponseEntity eqList(){
        List<Equipment> eqList = service.eqListService();
        if(eqList!=null) return ResponseEntity.ok(eqList);
        else return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity eqInsert(@RequestBody Equipment eq){
        boolean insertResult = service.eqInsertService(eq);
        if(insertResult) return ResponseEntity.ok().build();
        else return ResponseEntity.unprocessableEntity().build();
    }

    @DeleteMapping("{varId}")
    public ResponseEntity eqDelete(@PathVariable int varId) {
        boolean deleteResult = service.eqDeleteService(varId);
        if (deleteResult) return ResponseEntity.ok().build();
        else return ResponseEntity.unprocessableEntity().build();
    }
}

package com.matthewoks.secondStep.controllers;

import com.matthewoks.secondStep.models.Equipment;
import com.matthewoks.secondStep.models.Equipment;
import com.matthewoks.secondStep.models.User;
import com.matthewoks.secondStep.models.Workout;
import com.matthewoks.secondStep.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/equipments")
public class EquipmentController {
    @Autowired
    private EquipmentRepository repo;

    @GetMapping
    public List<Equipment> getAll(){
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Equipment> getById(@PathVariable int id){
        return repo.findById((long)id);
    }

    @PostMapping
    public Equipment save(@RequestBody Equipment eq) {
        return repo.save(eq);
    }
    @DeleteMapping("{varId}")
    public void delete(@PathVariable int varId) {
        if(varId!=0) repo.deleteById((long) varId);

    }

//    @GetMapping
//    public ResponseEntity eqList(){
//        List<Equipment> eqList = service.eqListService();
//        if(eqList!=null) return ResponseEntity.ok(eqList);
//        else return ResponseEntity.notFound().build();
//
//    }
//
//    @PostMapping
//    public ResponseEntity eqInsert(@RequestBody Equipment eq){
//        if(eq != null) {
//        if(service.eqInsertService(eq)) return ResponseEntity.ok().build();
//        else return ResponseEntity.unprocessableEntity().build();
//        } else return ResponseEntity.unprocessableEntity().build();
//    }
//
//    @PatchMapping("{varId}")
//    public ResponseEntity eqUpdate(@PathVariable long varId, @RequestBody Equipment eq){
//        if(varId!=0){
//            eq.setId(varId);
//            if(service.eqUpdateService(eq)) return ResponseEntity.ok().build();
//            else return ResponseEntity.unprocessableEntity().build();
//        } else return ResponseEntity.unprocessableEntity().build();
//
//
//    }
//
//    @DeleteMapping("{varId}")
//    public ResponseEntity eqDelete(@PathVariable int varId) {
//        if(varId!=0) {
//            if (service.eqDeleteService(varId)) return ResponseEntity.ok().build();
//            else return ResponseEntity.unprocessableEntity().build();
//        } else return ResponseEntity.unprocessableEntity().build();
//    }
}

package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.dto.ExerciseEquipmentDTO;
import com.matthewoks.secondStep.models.Equipment;
import com.matthewoks.secondStep.models.Exercise;
import com.matthewoks.secondStep.models.ExerciseEquipment;
import com.matthewoks.secondStep.repositories.EquipmentRepository;
import com.matthewoks.secondStep.repositories.ExerciseEquipmentRepository;
import com.matthewoks.secondStep.repositories.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExerciseEquipmentService {

    @Autowired
    private ExerciseEquipmentRepository repository;
    @Autowired
    private EquipmentRepository repositoryEq;
    @Autowired
    private ExerciseRepository repositoryEx;

    public ExerciseEquipment create(ExerciseEquipmentDTO dto) {
        Exercise exercise = repositoryEx.findById(dto.getExerciseId())
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found"));

        Equipment equipment = repositoryEq.findById(dto.getEquipmentId())
                .orElseThrow(() -> new IllegalArgumentException("Equipment not found"));

        ExerciseEquipment exerciseEquipment = new ExerciseEquipment();
        exerciseEquipment.setExercise(exercise);
        exerciseEquipment.setEquipment(equipment);

        return repository.save(exerciseEquipment);
    }

//    public ExerciseEquipment addExerciseEquipment(ExerciseEquipment ee) {
//        if (repository.existsByExerciseIdAndEquipmentId(
//                ee.getExercise().getId(),
//                ee.getEquipment().getId()
//        )) {
//            throw new IllegalArgumentException("Questa associazione esiste già");
//        }
//        return repository.save(ee);
//    }
    @Transactional
    public ExerciseEquipment addExerciseEquipment(ExerciseEquipment exerciseEquipment) {
        if (repository.existsByExerciseIdAndEquipmentId(
                exerciseEquipment.getExercise().getId(),
                exerciseEquipment.getEquipment().getId())) {
            throw new IllegalArgumentException("Questa relazione Exercise-Equipment esiste già");
        }
        return repository.save(exerciseEquipment);
    }

    @Transactional
    public List<ExerciseEquipment> addMultiple(List<ExerciseEquipment> list) {
        for (ExerciseEquipment ee : list) {
            if (repository.existsByExerciseIdAndEquipmentId(
                    ee.getExercise().getId(),
                    ee.getEquipment().getId())) {
                throw new IllegalArgumentException(
                        "Relazione già presente: exercise=" + ee.getExercise().getId() +
                                " equipment=" + ee.getEquipment().getId());
            }
        }
        return repository.saveAll(list);
    }

    public List<ExerciseEquipment> getAll() {
        return repository.findAll();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ExerciseEquipment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Relazione non trovata"));
    }

}


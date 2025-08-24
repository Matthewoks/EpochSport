package com.matthewoks.secondStep.services;

import com.matthewoks.secondStep.dto.ExerciseEquipmentDTO;
import com.matthewoks.secondStep.dto.ExerciseEquipmentResponseDTO;
import com.matthewoks.secondStep.models.Equipment;
import com.matthewoks.secondStep.models.Exercise;
import com.matthewoks.secondStep.models.ExerciseEquipment;
import com.matthewoks.secondStep.repositories.EquipmentRepository;
import com.matthewoks.secondStep.repositories.ExerciseEquipmentRepository;
import com.matthewoks.secondStep.repositories.ExerciseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseEquipmentService {

    @Autowired
    private ExerciseEquipmentRepository repo;
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

        return repo.save(exerciseEquipment);
    }

//    public ExerciseEquipment addExerciseEquipment(ExerciseEquipment ee) {
//        if (repo.existsByExerciseIdAndEquipmentId(
//                ee.getExercise().getId(),
//                ee.getEquipment().getId()
//        )) {
//            throw new IllegalArgumentException("Questa associazione esiste già");
//        }
//        return repo.save(ee);
//    }
    @Transactional
    public ExerciseEquipment addExerciseEquipment(ExerciseEquipment exerciseEquipment) {
        if (repo.existsByExerciseIdAndEquipmentId(
                exerciseEquipment.getExercise().getId(),
                exerciseEquipment.getEquipment().getId())) {
            throw new IllegalArgumentException("Questa relazione Exercise-Equipment esiste già");
        }
        return repo.save(exerciseEquipment);
    }

    @Transactional
    public List<ExerciseEquipmentResponseDTO> addMultiple(List<ExerciseEquipmentDTO> list) {
        List<ExerciseEquipment> daSalvare = new ArrayList<>();
        for (ExerciseEquipmentDTO ee : list) {

            Long exerciseId = ee.getExerciseId();
            Long equipmentId  = ee.getEquipmentId();
            if (repo.existsByExerciseIdAndEquipmentId(
                    exerciseId,
                    equipmentId )) {
                throw new IllegalArgumentException(
                        "Relazione già presente: exercise=" + exerciseId +
                                " equipment=" + equipmentId);
            }

            //controllo se i singoli esistono
            Exercise exercise = repositoryEx.findById(exerciseId)
                    .orElseThrow(() -> new IllegalArgumentException("Exercise non trovato: " + exerciseId));
            Equipment equipment = repositoryEq.findById(equipmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Equipment non trovato: " + equipmentId));

            ExerciseEquipment entity = new ExerciseEquipment();
            entity.setExercise(exercise);
            entity.setEquipment(equipment);
            daSalvare.add(entity);
        }
       // return repo.saveAll(daSalvare);

        // Salvo tutte le relazioni
        List<ExerciseEquipment> saved = repo.saveAll(daSalvare);

        // Converto in DTO
        return saved.stream()
                .map(rel -> {
                    ExerciseEquipmentResponseDTO dto = new ExerciseEquipmentResponseDTO();
                    dto.setExerciseId(rel.getExercise().getId());
                    dto.setEquipmentId(rel.getEquipment().getId());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public List<ExerciseEquipment> getAll() {
        return repo.findAll();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public ExerciseEquipment getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Relazione non trovata"));
    }

}


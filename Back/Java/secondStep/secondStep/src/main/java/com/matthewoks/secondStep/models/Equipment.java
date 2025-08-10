package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="equipments")
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    @Column(name="image_url")
    private String imageUrl;
    // Relazione inversa
//    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ExerciseEquipment> exerciseEquipments = new ArrayList<>();
//    @ManyToMany(mappedBy = "equipments")
//    private List<Exercise> exercises = new ArrayList<>();
    }

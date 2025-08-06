package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="Equipments")
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
    @OneToMany(mappedBy = "equipments")
    private Set<Exercise> exercises;
    }

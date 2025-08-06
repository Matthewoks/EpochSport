package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor

public abstract class PlannedAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String color;
    @Column(name="image_url")
    private String imageUrl;



    public PlannedAction(Long id, String name, String description, String color, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.imageUrl = imageUrl;
    }


}

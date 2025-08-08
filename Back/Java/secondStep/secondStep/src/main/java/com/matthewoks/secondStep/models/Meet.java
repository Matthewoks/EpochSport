package com.matthewoks.secondStep.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data //getters, setters, tostring, equals, hashcode
@NoArgsConstructor
public abstract class Meet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;



    public Meet(Long id,String color, String meetName) {
        this.id=id;
        this.name = meetName;
        this.color = color;
    }

}


package com.spring.aftas.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fish {

    @Id
    @NotBlank(message = "Name must not be blank")
    private String name;

    @NotNull(message = "Average weight must be provided")
    @Positive(message = "Average weight must be a positive number")
    @Column(nullable = false)
    private double averageWeight;

    @ManyToOne(cascade = CascadeType.ALL)
    private Level level;

    @OneToMany(mappedBy = "fish",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Hunting> huntings;

    public Fish(String trout, double v, Level level1) {
    }
}
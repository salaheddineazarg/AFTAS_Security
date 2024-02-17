package com.spring.aftas.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Level {

    @Id
    private long code;

    @NotBlank(message = "Description must not be blank")
    @Column(nullable = false)
    private String description;

    @Positive(message = "Points must be a positive number or zero")
    @Column(nullable = false)
    private int points;


}

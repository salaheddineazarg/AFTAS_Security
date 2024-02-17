package com.spring.aftas.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Hunting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Min(value = 1, message = "Number of fish must be at least 1")
    @Positive(message = "Number of fish must be a positive number")
    @Column(nullable = false)
    private int numberOfFish =1;

    @NotNull(message = "Fish must be provided")
    @ManyToOne(cascade = CascadeType.ALL)
    private Fish fish;

    @NotNull(message = "Competition must be provided")
    @ManyToOne(cascade = CascadeType.ALL)
    private Competition competition;

    @NotNull(message = "Member must be provided")
    @ManyToOne(cascade = CascadeType.ALL)
    private Member member;

    public Hunting(int i, Fish fish1, Member m1, Competition competition) {
    }
}

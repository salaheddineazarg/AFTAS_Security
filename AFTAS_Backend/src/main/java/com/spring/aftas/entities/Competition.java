package com.spring.aftas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competition {
    @Id
    @NotBlank(message = "Code must not be blank")
    @Pattern(regexp = "^.{3}-\\d{6}$")
    private String code;

    @NotNull(message = "Date must not be null")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
   // @Future(message = "The competition must be in future")
    private LocalDate date;

    @NotNull(message = "Start time must not be null")
    @Column(nullable = true)
    @Temporal(TemporalType.TIME)
    private LocalTime startTime;

    @NotNull(message = "End time must not be null")
    @Column(nullable = true)
    @Temporal(TemporalType.TIME)
    private LocalTime endTime;

    @Positive(message = "Number of participants must be a positive number or zero")
    @Min(value = 0, message = "Number of participants must be greater than or equal to 0")
    @Column(nullable = false)
    private int numberOfParticipants;

    @NotBlank(message = "Location must not be blank")
    @Column(nullable = false)
    private String location;

    @Positive(message = "Amount must be a positive number or zero")
    @Min(value = 0, message = "Amount must be greater than or equal to 0")
    @Column(nullable = false)
    private double amount;

    @OneToMany(mappedBy = "competition",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Hunting> huntings;

    @OneToMany(mappedBy = "competition",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rank> ranks;

    public Competition(String s, LocalDate of, LocalTime of1, LocalTime of2, int i, String hoceima, double v) {
    }
}

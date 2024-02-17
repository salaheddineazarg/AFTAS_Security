package com.spring.aftas.entities;


import com.spring.aftas.enumuration.IdentityDocType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {


    @Id
    private long num;

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Family name must not be blank")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String familyName;

    @NotNull(message = "Accession date must be provided")
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate accessionDate;

    @NotBlank(message = "Nationality must not be blank")
    @Size(min = 2, max = 50, message = "Nationality must be between 2 and 50 characters")
    @Column(nullable = false)
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocType identityDocumentType;

    @NotBlank(message = "Identity number must not be blank")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "Identity number must contain only letters and numbers")
    @Column(nullable = false,unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY,cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Hunting> huntings;

    @OneToMany(mappedBy = "member",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rank> ranks;



    public Member(String john, String doe, LocalDate now, String us, IdentityDocType identityDocType, String ab123456) {
    }
}

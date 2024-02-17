package com.spring.aftas.entities;


import com.spring.aftas.embedding.RankID;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rank {

    @EmbeddedId
    private RankID rankID;

    @PositiveOrZero(message = "Rank must be a positive number or zero")
    @Column(nullable = true)
    private int rank;

    @PositiveOrZero(message = "Score must be a positive number or zero")
    @Column(nullable = true)
    private int score;

    @ManyToOne
    @MapsId("competition_code")
    @NotNull(message = "Competition must be provided")
    private Competition competition;

    @ManyToOne
    @MapsId("member_num")
    @NotNull(message = "Member must be provided")
    private Member member;




    public Rank(RankID rankId, int i, int i1) {
    }
}

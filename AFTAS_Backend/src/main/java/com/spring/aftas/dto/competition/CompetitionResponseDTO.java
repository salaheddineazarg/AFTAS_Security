package com.spring.aftas.dto.competition;

import com.spring.aftas.dto.hunting.HuntingDTO;
import com.spring.aftas.dto.rank.RankDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionResponseDTO {

    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private double amount;
    private List<HuntingDTO> huntings;
    private List<RankDTO> ranks;
}

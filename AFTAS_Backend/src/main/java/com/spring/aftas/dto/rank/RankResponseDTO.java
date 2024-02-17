package com.spring.aftas.dto.rank;

import com.spring.aftas.dto.competition.CompetitionDTO;
import com.spring.aftas.dto.member.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RankResponseDTO {

    private int rank;
    private int score;
    private CompetitionDTO competition;
    private MemberDTO member;
}

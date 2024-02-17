package com.spring.aftas.dto.member;

import com.spring.aftas.dto.hunting.HuntingDTO;
import com.spring.aftas.dto.rank.RankDTO;
import com.spring.aftas.enumuration.IdentityDocType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {
    private long num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocType identityDocumentType;
    private String identityNumber;
    private List<HuntingDTO> huntings;
    private List<RankDTO> ranks;

}

package com.spring.aftas.dto.member;


import com.spring.aftas.enumuration.IdentityDocType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private long num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocType identityDocumentType;
    private String identityNumber;


}

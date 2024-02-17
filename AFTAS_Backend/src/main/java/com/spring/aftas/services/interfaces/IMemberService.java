package com.spring.aftas.services.interfaces;

import com.spring.aftas.dto.member.*;

import java.util.List;

public interface IMemberService extends IData<MemberResponseDTO, MemberDTO,Long>{


    List<MemberResponseDTO> findByFamilyNameOrName(String word);

}

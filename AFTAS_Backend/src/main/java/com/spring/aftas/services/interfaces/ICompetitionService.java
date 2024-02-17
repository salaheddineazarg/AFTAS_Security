package com.spring.aftas.services.interfaces;

import com.spring.aftas.dto.competition.CompetitionDTO;
import com.spring.aftas.dto.competition.CompetitionResponseDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;


public interface ICompetitionService extends IData<CompetitionResponseDTO, CompetitionDTO,String> {


    Page<CompetitionResponseDTO> getCompetitionsByPagination(Pageable pageable);
}

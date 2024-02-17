package com.spring.aftas.services.interfaces;


import com.spring.aftas.dto.rank.*;
import com.spring.aftas.embedding.RankID;

import java.util.List;
import java.util.Optional;

public interface IRankService {

    List<RankResponseDTO> getAllService();
    Optional<RankResponseDTO> saveService(RankDTO rankDTO);
    Optional<RankResponseDTO> findByIdService(String code ,long num);
    boolean deleteService(String code, long num);
    Optional<RankResponseDTO> updateService(RankDTO rankDTO,String code,long num);

    List<RankResponseDTO> getByCompetition(String code);
    boolean updateScore(long num,String code,String name,int numberOfFish);
    List<RankResponseDTO> calRanking(String code);
}

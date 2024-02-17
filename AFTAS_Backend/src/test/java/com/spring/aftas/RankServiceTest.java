package com.spring.aftas;


import com.spring.aftas.dto.rank.RankResponseDTO;
import com.spring.aftas.embedding.RankID;
import com.spring.aftas.entities.*;
import com.spring.aftas.enumuration.IdentityDocType;
import com.spring.aftas.repositories.RankRepository;
import com.spring.aftas.services.Impl.RankService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RankServiceTest {

    @InjectMocks
    private RankService rankService;

    @Mock
    private RankRepository rankRepository;


    @BeforeEach
    public void setUp(){
        Competition competition = new Competition("hoc-191223", LocalDate.of(2023,12,18), LocalTime.of(10, 0), LocalTime.of(12, 0), 50, "hoceima", 1000.0);  // You may need to set up a valid Competition object
        Level level1 = new Level(1,"level 1",1);
        Fish fish1 = new Fish("Trout", 2.5, level1);
        Object IdentityDocumentType;
        Member m1 = new Member("John", "Doe", LocalDate.now(), "US", IdentityDocType.Passport, "AB123456");
        Hunting hunting = new Hunting(2,fish1,m1,competition);

        RankID rankId= new RankID();
        rankId.setMember_num(m1.getNum());
        rankId.setCompetition_code(competition.getCode());

        Rank rank = new Rank(rankId,0,0);

    }



    @Test
    public  void calRankingTest(){
        List<RankResponseDTO> listResponse = new ArrayList<>();
        List<Rank> rankList = new ArrayList<>();
        when(rankRepository.findAllByCompetition_CodeOrderByScoreDesc("hoc-191223")).thenReturn(rankList);
        when(rankService.calRanking("hoc-191223")).thenReturn(listResponse);
        assertEquals(listResponse.size(), 0);
    }




}

package com.spring.aftas.repositories;

import com.spring.aftas.entities.Hunting;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HuntingRepository extends JpaRepository<Hunting,Long> {

   Optional<Hunting> findHuntingByCompetition_CodeAndMember_NumAndFish_Name(String code,long num,String name);
}

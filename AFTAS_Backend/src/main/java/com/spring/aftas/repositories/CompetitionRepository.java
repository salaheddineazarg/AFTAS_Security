package com.spring.aftas.repositories;

import com.spring.aftas.entities.Competition;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.time.LocalDate;

public interface CompetitionRepository extends JpaRepository<Competition,String> {

    boolean existsCompetitionByDate(LocalDate date);
}

package com.spring.aftas.repositories;

import com.spring.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish,String> {

    Optional<Fish> findByName(String name);
}

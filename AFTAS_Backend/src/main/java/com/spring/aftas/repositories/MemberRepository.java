package com.spring.aftas.repositories;

import com.spring.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.name LIKE %:keyword% OR m.familyName LIKE %:keyword%")
    List<Member> findByNameOrFamilyName(@Param("keyword") String keyword);
    boolean existsMemberByIdentityNumber(String identityNumber);

}

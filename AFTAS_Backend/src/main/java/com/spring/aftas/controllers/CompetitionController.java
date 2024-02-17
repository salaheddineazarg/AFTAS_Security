package com.spring.aftas.controllers;


import com.spring.aftas.dto.competition.CompetitionDTO;
import com.spring.aftas.dto.competition.CompetitionResponseDTO;
import com.spring.aftas.services.Impl.CompetitionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
@RequestMapping("api/competition")
public class CompetitionController {


    private final CompetitionService competitionService;


    public CompetitionController(CompetitionService competitionService){
        this.competitionService = competitionService;
    }



    @GetMapping
    public ResponseEntity<Page<CompetitionResponseDTO>> getAllByPagination(Pageable pageable){

        return new ResponseEntity<>(this.competitionService.getCompetitionsByPagination(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CompetitionResponseDTO> save(@Valid @RequestBody CompetitionDTO competitionDTO){

        return this.competitionService.saveService(competitionDTO)
                .map(competition -> new ResponseEntity<>(competition,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }


    @DeleteMapping("{code}")
    public  ResponseEntity<String> delete(@Size(min = 10) @PathVariable String code){

        if (this.competitionService.deleteService(code)){

            return new ResponseEntity<>("Competition is deleted successfuly!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Competition didn't deleted ",HttpStatus.NO_CONTENT);
    }

    @PutMapping("{code}")
    public ResponseEntity<CompetitionResponseDTO> update(@Valid @RequestBody CompetitionDTO competitionDTO,@Size(min = 10) @PathVariable String code){

        return this.competitionService.updateService(competitionDTO,code)
                .map(competition -> new ResponseEntity<>(competition,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @GetMapping("{code}")
    public ResponseEntity<CompetitionResponseDTO> getBycode(@Size(min = 10) @PathVariable String code){

        return this.competitionService.findByIdService(code)
                .map(competition -> new ResponseEntity<>(competition,HttpStatus.OK))
                .orElse(new ResponseEntity<>(null,HttpStatus.NOT_FOUND));

    }

}

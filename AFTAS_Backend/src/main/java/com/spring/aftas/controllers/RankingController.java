package com.spring.aftas.controllers;


import com.spring.aftas.dto.rank.RankDTO;
import com.spring.aftas.dto.rank.RankResponseDTO;
import com.spring.aftas.embedding.RankID;
import com.spring.aftas.entities.Rank;
import com.spring.aftas.services.Impl.RankService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ranking")
public class RankingController {


    private final RankService rankService;



    @Autowired
    public RankingController(RankService rankService){
        this.rankService =rankService;
    }



    @GetMapping
    public ResponseEntity<List<RankResponseDTO>> getAll(){

        return new ResponseEntity<>(this.rankService.getAllService(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<RankResponseDTO> save(@Valid @RequestBody RankDTO rankDTO){

        return this.rankService.saveService(rankDTO)
                .map(rankResponseDTO -> new ResponseEntity<>(rankResponseDTO,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }


    @PutMapping
    public ResponseEntity<RankResponseDTO> update(@Valid @RequestBody RankDTO rankDTO,@RequestParam String code,@RequestParam long num){

        return this.rankService.updateService(rankDTO,code,num)
                .map(rank-> new ResponseEntity<>(rank,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }


    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam String code,@RequestParam long num){
        if (this.rankService.deleteService(code,num)){
            return new ResponseEntity<>("Ranking is deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Ranking didn't deleted",HttpStatus.NO_CONTENT);
    }


    @GetMapping("get")
    public ResponseEntity<RankResponseDTO> getById(@RequestParam String code,@RequestParam long num){

        return this.rankService.findByIdService(code,num)
                .map(rankResponseDTO -> new ResponseEntity<>(rankResponseDTO,HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }

    @GetMapping("competition")
    public ResponseEntity<List<RankResponseDTO>> getByCompetition(@RequestParam String code){

        return new ResponseEntity<>(this.rankService.getByCompetition(code),HttpStatus.OK);
    }

    @GetMapping("podium/{code}")
    public ResponseEntity<List<RankResponseDTO>> getPodium(@PathVariable String code){

        return new ResponseEntity<>(this.rankService.calRanking(code),HttpStatus.OK);
    }


}

package com.spring.aftas.controllers;


import com.spring.aftas.dto.hunting.HuntingDTO;
import com.spring.aftas.dto.hunting.HuntingResponseDTO;
import com.spring.aftas.services.Impl.HuntingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hunting")
public class HuntingController {


    private  final HuntingService huntingService;

    @Autowired
    public HuntingController(HuntingService huntingService){

        this.huntingService = huntingService;
    }


    @GetMapping
    public ResponseEntity<List<HuntingResponseDTO>> getAll(){

        return new ResponseEntity<>(this.huntingService.getAllService(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HuntingResponseDTO> save(@Valid @RequestBody HuntingDTO huntingDTO){

        return this.huntingService.saveService(huntingDTO)
                .map(hunting->new ResponseEntity<>(hunting,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @PutMapping("{id}")
    public ResponseEntity<HuntingResponseDTO> update(@Valid @RequestBody HuntingDTO huntingDTO, @Min(value = 1) @PathVariable long id ){

        return this.huntingService.updateService(huntingDTO,id)
                .map(hunting->new ResponseEntity<>(hunting,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@Min(value = 1) @PathVariable long id){

        if (this.huntingService.deleteService(id)){
            return new ResponseEntity<>("hunting is deleted successfully !",HttpStatus.OK);
        }

        return new ResponseEntity<>("hunting didn't deleted",HttpStatus.NO_CONTENT);
    }


    @GetMapping("{id}")
    public ResponseEntity<HuntingResponseDTO> getById(@Min(value = 1) @PathVariable long id){

        return this.huntingService.findByIdService(id)
                .map(hunting -> new ResponseEntity<>(hunting,HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }
}

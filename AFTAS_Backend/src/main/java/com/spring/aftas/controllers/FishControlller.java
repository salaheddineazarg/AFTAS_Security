package com.spring.aftas.controllers;


import com.spring.aftas.dto.fish.FishDTO;
import com.spring.aftas.dto.fish.FishResponseDTO;
import com.spring.aftas.services.Impl.FishService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fish")
public class FishControlller {
    private final FishService fishService;

    public FishControlller(FishService fishService){
        this.fishService = fishService;
    }


    @GetMapping
    public ResponseEntity<List<FishResponseDTO>> getAll(){

        return  new ResponseEntity<>(this.fishService.getAllService(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<FishResponseDTO> save(@Valid @RequestBody FishDTO fishDTO){

        return this.fishService.saveService(fishDTO)
                .map(fishResponseDTO -> new ResponseEntity<>(fishResponseDTO,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @DeleteMapping("{name}")
    public ResponseEntity<String> delete(@NotBlank @PathVariable String name){
        if (this.fishService.deleteService(name)){
            return new ResponseEntity<>("Answer is deleted successfuly!",HttpStatus.OK);
        }

        return new ResponseEntity<>("Answer is didn't deleted !",HttpStatus.NO_CONTENT);
    }

    @PutMapping("{name}")
    public ResponseEntity<FishResponseDTO> update(@Valid @RequestBody FishDTO fishDTO,@NotBlank @PathVariable String name){

        return this.fishService.updateService(fishDTO,name)
                .map(fishResponseDTO -> new ResponseEntity<>(fishResponseDTO,HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @GetMapping("{name}")
    public ResponseEntity<FishResponseDTO> getByName(@NotBlank @PathVariable String name ){

        return this.fishService.findByIdService(name)
                .map(fishResponseDTO -> new ResponseEntity<>(fishResponseDTO,HttpStatus.FOUND))
                .orElse(new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }


}

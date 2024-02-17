package com.spring.aftas.controllers;


import com.spring.aftas.dto.level.LevelDTO;
import com.spring.aftas.services.Impl.LevelService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/level")
public class LevelController {


    private final LevelService levelService;


    public LevelController(LevelService levelService){
        this.levelService = levelService;
    }


    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAll(){

        return new ResponseEntity<>(this.levelService.getAllService(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LevelDTO> save(@Valid @RequestBody LevelDTO levelDTO){

        return this.levelService.saveService(levelDTO)
                .map(level ->new ResponseEntity<>(level,HttpStatus.CREATED) )
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @DeleteMapping("{code}")
    public ResponseEntity<String> delete(@Min(value = 1) @PathVariable long code){

        if (this.levelService.deleteService(code)){

            return new ResponseEntity<>("Level is delete successfuly!",HttpStatus.OK);
        }
        return new ResponseEntity<>("Level didn't deleted",HttpStatus.NO_CONTENT);
    }

    @PutMapping("{code}")
    public ResponseEntity<LevelDTO> update(@Valid @RequestBody LevelDTO levelDTO,@Min(value = 1) @PathVariable long code){
        return this.levelService.updateService(levelDTO,code)
                .map(level ->new ResponseEntity<>(level,HttpStatus.CREATED) )
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @GetMapping("{code}")
    public ResponseEntity<LevelDTO> getByCode(@Min(value = 1) @PathVariable long code){
        return this.levelService.findByIdService(code)
                .map(level ->new ResponseEntity<>(level,HttpStatus.CREATED) )
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }
}

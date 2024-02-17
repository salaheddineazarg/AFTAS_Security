package com.spring.aftas.services.Impl;


import com.spring.aftas.dto.fish.FishDTO;
import com.spring.aftas.dto.fish.FishResponseDTO;
import com.spring.aftas.entities.Fish;
import com.spring.aftas.repositories.FishRepository;
import com.spring.aftas.repositories.LevelRepository;
import com.spring.aftas.services.interfaces.IFishService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class FishService implements IFishService {

    private final ModelMapper modelMapper;
    private final FishRepository fishRepository;
    private final LevelRepository levelRepository;



    private FishService(
            ModelMapper modelMapper,
            FishRepository fishRepository,
            LevelRepository levelRepository

    ){
        this.fishRepository = fishRepository;
        this.modelMapper = modelMapper;
        this.levelRepository= levelRepository;
    }

    @Override
    public List<FishResponseDTO> getAllService() {

        return Arrays.asList(modelMapper.map(this.fishRepository.findAll(), FishResponseDTO[].class));
    }

    @Override
    public Optional<FishResponseDTO> saveService(FishDTO fishDTO) {

        Fish fish =  modelMapper.map(fishDTO,Fish.class);

        if (fishDTO.getLevel_code()>0){
            fish.setLevel(
                   this.levelRepository.findById(fishDTO.getLevel_code()).get()
            );
        }

        fish = this.fishRepository.save(fish);

        return Optional.ofNullable(modelMapper.map(fish, FishResponseDTO.class));
    }

    @Override
    public boolean deleteService(String name) {
        if (this.fishRepository.existsById(name)){
            this.fishRepository.deleteById(name);
            return  true;
        }
        return false;
    }

    @Override
    public Optional<FishResponseDTO> updateService(FishDTO fishDTO, String name) {
        if(this.fishRepository.existsById(name)){
            Fish fish =  modelMapper.map(fishDTO,Fish.class);

            if (fishDTO.getLevel_code()>0){
                fish.setLevel(
                        this.levelRepository.findById(fishDTO.getLevel_code()).get()
                );
            }

            fish = this.fishRepository.save(fish);

            return Optional.ofNullable(modelMapper.map(fish, FishResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<FishResponseDTO> findByIdService(String  name) {
        Optional<Fish> fishOptional = this.fishRepository.findByName(name);
        return fishOptional
                .map(fish -> modelMapper.map(fish,FishResponseDTO.class));
    }
}

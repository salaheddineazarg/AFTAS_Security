package com.spring.aftas.services.Impl;

import com.spring.aftas.dto.level.LevelDTO;
import com.spring.aftas.entities.Level;
import com.spring.aftas.repositories.LevelRepository;
import com.spring.aftas.services.interfaces.ILevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LevelService  implements ILevelService {


    private  final ModelMapper modelMapper;
    private final LevelRepository levelRepository;



    public LevelService(ModelMapper modelMapper,LevelRepository levelRepository){
        this.levelRepository = levelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<LevelDTO> getAllService() {


        return Arrays.asList(modelMapper.map(this.levelRepository.findAll(),LevelDTO[].class));
    }

    @Override
    public Optional<LevelDTO> saveService(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO,Level.class);
        List<Level> levels = levelRepository.findAllByPointsGreaterThanEqual(level.getPoints());
        if(levels.isEmpty()){
            level = this.levelRepository.save(level);
            return Optional.ofNullable(modelMapper.map(level,LevelDTO.class));
        }
        return Optional.empty();

    }

    @Override
    public boolean deleteService(Long Id) {
        if (this.levelRepository.existsById(Id)){
            this.levelRepository.deleteById(Id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<LevelDTO> updateService(LevelDTO levelDTO, Long Id) {
        if (this.levelRepository.existsById(Id)){
            Level level = modelMapper.map(levelDTO,Level.class);
            level = this.levelRepository.save(level);

            return Optional.of(modelMapper.map(level,LevelDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<LevelDTO> findByIdService(Long Id) {
        if (this.levelRepository.existsById(Id)){
            Optional<Level> levelOptional = this.levelRepository.findById(Id);

            return levelOptional.map(level -> modelMapper.map(level,LevelDTO.class));
        }
        return Optional.empty();

    }
}

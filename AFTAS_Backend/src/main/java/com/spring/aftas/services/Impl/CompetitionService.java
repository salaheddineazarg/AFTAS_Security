package com.spring.aftas.services.Impl;


import com.spring.aftas.dto.competition.CompetitionDTO;
import com.spring.aftas.dto.competition.CompetitionResponseDTO;
import com.spring.aftas.entities.Competition;
import com.spring.aftas.repositories.CompetitionRepository;
import com.spring.aftas.services.interfaces.ICompetitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import org.springframework.data.domain.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionService implements ICompetitionService {

    private final ModelMapper modelMapper;
    private final CompetitionRepository competitionRepository;

    public CompetitionService(ModelMapper modelMapper,CompetitionRepository competitionRepository){
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<CompetitionResponseDTO> getCompetitionsByPagination(Pageable pageable) {

        Page<Competition> competitionPage = this.competitionRepository.findAll(pageable);

       return competitionPage.map(competition -> modelMapper.map(competition,CompetitionResponseDTO.class));
    }


    @Override
    public List<CompetitionResponseDTO> getAllService() {

        return null;
    }

    @Override
    public Optional<CompetitionResponseDTO> saveService(CompetitionDTO competitionDTO) {
        if (this.competitionRepository.existsCompetitionByDate(competitionDTO.getDate())){

          return Optional.ofNullable(null);
        }else {
            Competition competition = modelMapper.map(competitionDTO,Competition.class);

            competition = this.competitionRepository.save(competition);

            return Optional.of(modelMapper.map(competition,CompetitionResponseDTO.class));
        }
    }

    @Override
    public boolean deleteService(String Id) {
        if (this.competitionRepository.existsById(Id)){

            this.competitionRepository.deleteById(Id);
            return true;
        }
        System.out.println("exist");
        return false;
    }

    @Override
    public Optional<CompetitionResponseDTO> updateService(CompetitionDTO competitionDTO, String Id) {
        if (this.competitionRepository.existsById(Id)){
            Competition competition = modelMapper.map(competitionDTO,Competition.class);

            competition = this.competitionRepository.save(competition);

            return Optional.of(modelMapper.map(competition,CompetitionResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<CompetitionResponseDTO> findByIdService(String Id) {
        if (this.competitionRepository.existsById(Id)){
            Optional<Competition> competitionOptional = this.competitionRepository.findById(Id);
            return competitionOptional.map(competition -> modelMapper.map(competition,CompetitionResponseDTO.class));
        }
        return Optional.empty();
    }
}

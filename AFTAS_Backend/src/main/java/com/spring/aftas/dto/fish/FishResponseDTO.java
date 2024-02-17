package com.spring.aftas.dto.fish;

import com.spring.aftas.dto.level.LevelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FishResponseDTO {

    private String name;
    private double averageWeight;
    private LevelDTO level;
}

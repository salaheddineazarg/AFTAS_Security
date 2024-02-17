package com.spring.aftas.dto.fish;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishDTO {

    private String name;
    private double averageWeight;
    private long level_code;
}

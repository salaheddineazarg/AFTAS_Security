package com.spring.aftas.dto.level;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LevelDTO {

    private long code;
    private String description;
    private int points;
}

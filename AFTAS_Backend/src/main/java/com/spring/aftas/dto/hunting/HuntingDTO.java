package com.spring.aftas.dto.hunting;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HuntingDTO {
    private long id;
    private int numberOfFish;
    private String fish_name;
    private long member_num;
    private String competition_code;

}

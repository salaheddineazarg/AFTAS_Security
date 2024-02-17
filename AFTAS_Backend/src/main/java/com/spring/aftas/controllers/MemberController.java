package com.spring.aftas.controllers;


import com.spring.aftas.dto.member.MemberDTO;
import com.spring.aftas.dto.member.MemberResponseDTO;
import com.spring.aftas.services.Impl.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/member")
public class MemberController {


    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }


    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAll(){

        return new ResponseEntity<>(this.memberService.getAllService(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<MemberResponseDTO> save(@Valid @RequestBody MemberDTO memberDTO){
        return this.memberService.saveService(memberDTO)
                .map(member -> new ResponseEntity<>(member,HttpStatus.CREATED) )
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));

    }


    @DeleteMapping("{num}")
    public ResponseEntity<String> delete(@PathVariable long num){

        if (this.memberService.deleteService(num)){

            return new ResponseEntity<>("Member is delete successfuly",HttpStatus.OK);
        }
        return new ResponseEntity<>("Member didn't deleted",HttpStatus.NO_CONTENT);
    }


    @PutMapping("{num}")
    public ResponseEntity<MemberResponseDTO> update(@Valid @RequestBody MemberDTO memberDTO, @Min(value = 1) @PathVariable long num){

        return this.memberService.updateService(memberDTO,num)
                .map(memberResponseDTO -> new ResponseEntity<>(memberResponseDTO,HttpStatus.CREATED) )
                .orElse(new ResponseEntity<>(null,HttpStatus.OK));
    }

    @GetMapping("{num}")
    public ResponseEntity<MemberResponseDTO> getByNum(@Min(value = 1) @PathVariable long num){

        return this.memberService.findByIdService(num)
                .map(memberResponseDTO -> new ResponseEntity<>(memberResponseDTO,HttpStatus.FOUND) )
                .orElse(new ResponseEntity<>(null,HttpStatus.NOT_FOUND));
    }
    @GetMapping("/search")
    public ResponseEntity<List<MemberResponseDTO>> getByFamilyNameOrNameOrNum(@RequestParam String keyword) {
        return new ResponseEntity<>(this.memberService.findByFamilyNameOrName(keyword),HttpStatus.OK);
    }



}

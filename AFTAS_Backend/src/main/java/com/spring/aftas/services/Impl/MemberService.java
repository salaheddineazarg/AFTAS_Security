package com.spring.aftas.services.Impl;

import com.spring.aftas.dto.member.MemberDTO;
import com.spring.aftas.dto.member.MemberResponseDTO;
import com.spring.aftas.entities.Member;
import com.spring.aftas.repositories.MemberRepository;
import com.spring.aftas.services.interfaces.IMemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class MemberService implements IMemberService {


    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;



    public MemberService(ModelMapper modelMapper, MemberRepository memberRepository) {
        this.modelMapper = modelMapper;
        this.memberRepository = memberRepository;
    }


    @Override
    public List<MemberResponseDTO> getAllService() {

        return Arrays.asList(modelMapper.map(this.memberRepository.findAll(), MemberResponseDTO[].class));
    }

    @Override
    public Optional<MemberResponseDTO> saveService(MemberDTO memberDTO) {
        if (this.memberRepository.existsMemberByIdentityNumber(memberDTO.getIdentityNumber())){
            return Optional.empty();
        }else {
            Member member = modelMapper.map(memberDTO,Member.class);
            member = this.memberRepository.save(member);
            System.out.println(member);
            return Optional.of(modelMapper.map(member, MemberResponseDTO.class));
        }
    }

    @Override
    public boolean deleteService(Long Id) {
        if (this.memberRepository.existsById(Id)){
            this.memberRepository.deleteById(Id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<MemberResponseDTO> updateService(MemberDTO memberDTO, Long Id) {
        if (this.memberRepository.existsById(Id)){
            Member member = modelMapper.map(memberDTO, Member.class);
            member.setNum(Id);
            member = this.memberRepository.save(member);
            return Optional.ofNullable(modelMapper.map(member, MemberResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public Optional<MemberResponseDTO> findByIdService(Long Id) {
        if (this.memberRepository.existsById(Id)){
        Optional<Member> memberOptional = this.memberRepository.findById(Id);
        return memberOptional.map(member -> modelMapper.map(member,MemberResponseDTO.class));
        }
        return Optional.empty();
    }

    @Override
    public List<MemberResponseDTO> findByFamilyNameOrName(String keyword) {

        return Arrays.asList(modelMapper.map(this.memberRepository.findByNameOrFamilyName(keyword),MemberResponseDTO[].class));
    }
}

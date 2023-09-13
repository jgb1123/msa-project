package com.example.memberservice.member.mapper;

import com.example.memberservice.member.dto.MemberPatchDTO;
import com.example.memberservice.member.dto.MemberPostDTO;
import com.example.memberservice.member.dto.MemberResponseDTO;
import com.example.memberservice.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    public Member memberPostDTOToMember(MemberPostDTO memberPostDTO) {
        return Member.builder()
                .email(memberPostDTO.getEmail())
                .password(memberPostDTO.getPassword())
                .name(memberPostDTO.getName())
                .build();
    }

    public Member memberPatchDTOToMember(MemberPatchDTO memberPatchDTO) {
        return Member.builder()
                .password(memberPatchDTO.getPassword())
                .build();
    }

    public MemberResponseDTO memberToMemberResponseDTO(Member member) {
        return MemberResponseDTO.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .build();
    }
}

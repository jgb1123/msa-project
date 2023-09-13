package com.example.memberservice.member.service;

import com.example.memberservice.exception.BusinessLogicException;
import com.example.memberservice.exception.ExceptionCode;
import com.example.memberservice.member.dto.MemberPostDTO;
import com.example.memberservice.member.dto.MemberResponseDTO;
import com.example.memberservice.member.entity.Member;
import com.example.memberservice.member.mapper.MemberMapper;
import com.example.memberservice.member.respository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;

    public MemberResponseDTO createMember(MemberPostDTO memberPostDTO) {
        Member member = memberMapper.memberPostDTOToMember(memberPostDTO);
        member.setMemberId(UUID.randomUUID().toString());
        member.setEncodedPassword(encoder.encode(member.getPassword()));

        Member savedMember = memberRepository.save(member);

        return memberMapper.memberToMemberResponseDTO(savedMember);
    }

    public MemberResponseDTO findMember(String memberId) {
        Member foundMember = getVerifiedMemberById(memberId);
        return memberMapper.memberToMemberResponseDTO(foundMember);
    }

    private Member getVerifiedMemberById(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

}

package com.example.memberservice.security.service;

import com.example.memberservice.exception.BusinessLogicException;
import com.example.memberservice.exception.ExceptionCode;
import com.example.memberservice.member.entity.Member;
import com.example.memberservice.member.respository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member foundMember = memberRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        return new User(foundMember.getEmail(), foundMember.getPassword(),
                true, true, true, true, new ArrayList<>());
    }
}

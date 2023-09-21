package com.example.memberservice.member.controller;

import com.example.memberservice.member.dto.MemberPostDTO;
import com.example.memberservice.member.dto.MemberResponseDTO;
import com.example.memberservice.member.service.MemberService;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    @Timed(value = "members.create", longTask = true)
    public ResponseEntity<MemberResponseDTO> createMember(@RequestBody MemberPostDTO memberPostDTO) {
        MemberResponseDTO memberResponseDTO = memberService.createMember(memberPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponseDTO);
    }

    @GetMapping("/find/{memberId}")
    @Timed(value = "members.find", longTask = true)
    public ResponseEntity<MemberResponseDTO> findMember(@PathVariable String memberId) {
        MemberResponseDTO memberResponseDTO = memberService.findMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDTO);
    }
}

package com.example.storeservice.client;

import com.example.storeservice.dto.MemberResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-service")
public interface MemberServiceClient {

    @GetMapping("find/{memberId}")
    MemberResponseDTO getMember(@PathVariable String memberId);
}

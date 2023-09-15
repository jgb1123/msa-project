package com.example.memberservice.client;

import com.example.memberservice.dto.MultiResponseDTO;
import com.example.memberservice.member.dto.StoreResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="store-service")
public interface StoreServiceClient {
    @GetMapping("/find/{storeCategoryId}")
    MultiResponseDTO<StoreResponseDTO> getStores(@PathVariable String storeCategoryId,
                                                 Pageable pageable);
}

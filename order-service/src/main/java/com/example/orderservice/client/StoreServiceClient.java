package com.example.orderservice.client;

import com.example.orderservice.dto.ItemResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "store-service")
public interface StoreServiceClient {

    @GetMapping("/item/{itemId}")
    ItemResponseDTO getItem(@PathVariable Long itemId);
}

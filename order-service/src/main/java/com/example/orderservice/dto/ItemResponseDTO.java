package com.example.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponseDTO {
    private Long itemId;
    private String itemName;
    private Integer price;
    private Integer soldCnt;
    private Integer stockCnt;
    private String info;
}

package com.example.storeservice.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPostDTO {
    private String itemName;
    private Integer price;
    private Integer stockCnt;
    private String info;
}

package com.example.storeservice.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorePostDTO {
    private String storeName;
    private String address;
    private String phone;
    private Integer minimumOrderPrice;
    private Integer deliveryFee;
    private String storeCategoryId;
    private String ownerMemberId;
}

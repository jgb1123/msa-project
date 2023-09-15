package com.example.storeservice.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreResponseDTO {
    private Long storeId;
    private String storeName;
    private String address;
    private String phone;
    private Integer minimumOrderPrice;
    private String storeCategory;
    private String ownerMemberId;
    private int deliveryFee;
}

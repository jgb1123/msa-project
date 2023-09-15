package com.example.storeservice.store.mapper;

import com.example.storeservice.store.dto.StorePostDTO;
import com.example.storeservice.store.dto.StoreResponseDTO;
import com.example.storeservice.store.entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {
    public Store storePostDTOToStore(StorePostDTO storePostDTO) {
        return Store.builder()
                .storeName(storePostDTO.getStoreName())
                .address(storePostDTO.getAddress())
                .phone(storePostDTO.getPhone())
                .minimumOrderPrice(storePostDTO.getMinimumOrderPrice())
                .deliveryFee(storePostDTO.getDeliveryFee())
                .ownerMemberId(storePostDTO.getOwnerMemberId())
                .build();
    }

    public StoreResponseDTO storeToStoreResponseDTO(Store store) {
        return StoreResponseDTO.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .address(store.getAddress())
                .phone(store.getPhone())
                .minimumOrderPrice(store.getMinimumOrderPrice())
                .storeCategory(store.getStoreCategory().getStoreCategoryId())
                .ownerMemberId(store.getOwnerMemberId())
                .deliveryFee(store.getDeliveryFee())
                .build();
    }
}

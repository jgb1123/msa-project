package com.example.storeservice.store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    private Long storeId;

    @Column
    private String storeName;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private Integer minimumOrderPrice;

    @Column
    private Integer deliveryFee;

    @Column
    private String ownerMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_CATEGORY_ID")
    private StoreCategory storeCategory;

    public void setStoreCategory(StoreCategory storeCategory) {
        if(this.storeCategory != null) {
            this.storeCategory.getStores().remove(this);
        }
        this.storeCategory = storeCategory;
        if(!storeCategory.getStores().contains(this)) {
            storeCategory.addStore(this);
        }
    }
}

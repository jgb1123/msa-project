package com.example.storeservice.store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreCategory {
    @Id
    private String storeCategoryId;

    @Column
    private String storeCategory;

    @Builder.Default
    @OneToMany(mappedBy = "storeCategory")
    private List<Store> stores = new ArrayList<>();

    public void addStore(Store store) {
        this.stores.add(store);
        if(store.getStoreCategory() != this) {
            store.setStoreCategory(this);
        }
    }
}

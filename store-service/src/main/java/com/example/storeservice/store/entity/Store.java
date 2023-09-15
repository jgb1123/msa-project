package com.example.storeservice.store.entity;

import com.example.storeservice.item.entity.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(
        name = "STORE_SEQ_GENERATOR",
        sequenceName = "STORE_SEQ",
        allocationSize = 1
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ_GENERATOR")
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

    @Builder.Default
    @OneToMany(mappedBy = "store")
    private List<Item> items = new ArrayList<>();

    public void setStoreCategory(StoreCategory storeCategory) {
        if(this.storeCategory != null) {
            this.storeCategory.getStores().remove(this);
        }
        this.storeCategory = storeCategory;
        if(!storeCategory.getStores().contains(this)) {
            storeCategory.addStore(this);
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
        if(item.getStore() != this) {
            item.setStore(this);
        }
    }
}

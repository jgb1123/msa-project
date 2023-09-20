package com.example.storeservice.item.entity;

import com.example.storeservice.store.entity.Store;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(
        name = "ITEM_SEQ_GENERATOR",
        sequenceName = "STORE_SEQ",
        allocationSize = 1
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ITEM_SEQ_GENERATOR")
    private Long itemId;

    @Column
    private String itemName;

    @Column
    @Builder.Default
    private Integer price = 0;

    @Column
    @Builder.Default
    private Integer soldCnt = 0;

    @Column
    @Builder.Default
    private Integer stockCnt = 0;

    @Column
    private String info;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public void setStore(Store store) {
        if(this.store != null) {
            this.store.getItems().remove(this);
        }
        this.store = store;
        if(!store.getItems().contains(this)) {
            store.addItem(this);
        }
    }

    public void setStockCnt(Integer itemOrderCnt) {
        this.stockCnt -= itemOrderCnt;
    }
}

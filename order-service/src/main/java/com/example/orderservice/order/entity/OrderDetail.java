package com.example.orderservice.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(
        name = "ORDER_DETAIL_SEQ_GENERATOR",
        sequenceName = "STORE_SEQ",
        allocationSize = 1
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_DETAIL_SEQ_GENERATOR")
    private Long orderDetailId;

    @Column
    private Integer itemOrderCnt;

    @Column
    private Long itemId;

    @Column
    private String itemName;

    @Column
    private Integer itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void setOrder(Order order) {
        if(this.order != null) {
            this.order.getOrderDetails().remove(this);
        }
        this.order = order;
        if(!order.getOrderDetails().contains(this)) {
            order.addOrderDetail(this);
        }
    }
}

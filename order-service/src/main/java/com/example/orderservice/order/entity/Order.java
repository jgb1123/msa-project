package com.example.orderservice.order.entity;

import com.example.orderservice.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity(name = "ORDERS")
@SequenceGenerator(
        name = "ORDER_SEQ_GENERATOR",
        sequenceName = "STORE_SEQ",
        allocationSize = 1
)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENERATOR")
    private Long orderId;

    @Column
    private Integer orderPrice;

    @Column
    private Long storeId;

    @Column
    private String memberId;

    @Builder.Default
    @Column
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @Builder.Default
    @BatchSize(size = 1000)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public void addOrderDetail(OrderDetail orderItem) {
        this.orderDetails.add(orderItem);
        if(orderItem.getOrder() != this) {
            orderItem.setOrder(this);
        }
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Getter
    public enum OrderStatus {
        ORDER_REQUEST(0, "주문 요청"),
        ORDER_CONFIRM(1, "주문 확정"),
        ORDER_COMPLETE(2, "주문 완료"),
        ORDER_CANCEL(3, "주문 취소");

        private static final Map<String, String> map = Collections.unmodifiableMap(
                Stream.of(values()).collect(Collectors.toMap(OrderStatus::getStepDescription, OrderStatus::name))
        );

        private final int stepNumber;

        private final String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
        public static OrderStatus of(String stepDescription){
            return OrderStatus.valueOf(map.get(stepDescription));
        }
    }
}

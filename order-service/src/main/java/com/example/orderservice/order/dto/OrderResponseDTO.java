package com.example.orderservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {
    private Long orderId;
    private String memberId;
    private Long storeId;
    private Integer orderPrice;
    private String orderStatus;
    private LocalDateTime createdAt;
    private List<OrderDetailResponseDTO> orderDetails;
}

package com.example.orderservice.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailPostDTO {
    @NotNull
    @Positive
    private Long itemId;

    @NotNull
    @Positive
    private Integer itemOrderCnt;
}

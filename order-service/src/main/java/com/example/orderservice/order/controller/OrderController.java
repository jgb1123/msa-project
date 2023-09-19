package com.example.orderservice.order.controller;

import com.example.orderservice.order.dto.OrderPostDTO;
import com.example.orderservice.order.dto.OrderResponseDTO;
import com.example.orderservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{memberId}/{storeId}")
    public ResponseEntity<OrderResponseDTO> createOrder(@PathVariable String memberId,
                                                        @PathVariable Long storeId,
                                                        @RequestBody OrderPostDTO orderPostDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(orderPostDTO, storeId, memberId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }


}

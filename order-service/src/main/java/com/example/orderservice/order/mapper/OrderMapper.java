package com.example.orderservice.order.mapper;

import com.example.orderservice.dto.ItemResponseDTO;
import com.example.orderservice.order.dto.OrderDetailPostDTO;
import com.example.orderservice.order.dto.OrderDetailResponseDTO;
import com.example.orderservice.order.dto.OrderPostDTO;
import com.example.orderservice.order.dto.OrderResponseDTO;
import com.example.orderservice.order.entity.Order;
import com.example.orderservice.order.entity.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public Order orderPostDTOToOrder(OrderPostDTO orderPostDTO, Long storeId, String memberId) {
        return Order.builder()
                .storeId(storeId)
                .memberId(memberId)
                .build();
    }

    public OrderDetail orderDetailPostDTOToOrderDetail(OrderDetailPostDTO orderDetailPostDTO, ItemResponseDTO itemResponseDTO) {
        return OrderDetail.builder()
                .itemOrderCnt(orderDetailPostDTO.getItemOrderCnt())
                .itemId(itemResponseDTO.getItemId())
                .itemName(itemResponseDTO.getItemName())
                .itemPrice(itemResponseDTO.getPrice())
                .build();
    }

    public OrderResponseDTO orderToOrderResponseDTO(Order order) {
        return OrderResponseDTO.builder()
                .orderId(order.getOrderId())
                .storeId(order.getStoreId())
                .memberId(order.getMemberId())
                .orderStatus(order.getOrderStatus().getStepDescription())
                .createdAt(order.getCreatedAt())
                .orderDetails(orderDetailsToOrderDetailResponseDTOs(order.getOrderDetails()))
                .orderPrice(order.getOrderPrice())
//                .deliveryFee(order.getStore().getDeliveryFee())
                .build();
    }

    public List<OrderResponseDTO> ordersToOrderResponseDTOs(List<Order> orders) {
        return orders
                .stream()
                .map(order -> OrderResponseDTO.builder()
                        .orderId(order.getOrderId())
                        .storeId(order.getStoreId())
                        .memberId(order.getMemberId())
                        .orderStatus(order.getOrderStatus().getStepDescription())
                        .createdAt(order.getCreatedAt())
                        .orderDetails(orderDetailsToOrderDetailResponseDTOs(order.getOrderDetails()))
                        .orderPrice(order.getOrderPrice())
//                        .deliveryFee(order.getStore().getDeliveryFee())
                        .build())
                .collect(Collectors.toList());
    }

    public List<OrderDetailResponseDTO> orderDetailsToOrderDetailResponseDTOs(List<OrderDetail> orderDetails) {
        return orderDetails
                .stream()
                .map(orderDetail -> OrderDetailResponseDTO.builder()
                        .orderDetailId(orderDetail.getOrderDetailId())
                        .itemOrderCnt(orderDetail.getItemOrderCnt())
                        .itemId(orderDetail.getItemId())
                        .itemName(orderDetail.getItemName())
                        .itemPrice(orderDetail.getItemPrice())
                        .build())
                .collect(Collectors.toList());
    }
}

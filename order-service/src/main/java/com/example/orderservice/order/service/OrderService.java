package com.example.orderservice.order.service;


import com.example.orderservice.client.StoreServiceClient;
import com.example.orderservice.dto.ItemResponseDTO;
import com.example.orderservice.messagequeue.KafkaProducer;
import com.example.orderservice.order.dto.OrderDetailPostDTO;
import com.example.orderservice.order.dto.OrderPostDTO;
import com.example.orderservice.order.dto.OrderResponseDTO;
import com.example.orderservice.order.entity.Order;
import com.example.orderservice.order.entity.OrderDetail;
import com.example.orderservice.order.mapper.OrderMapper;
import com.example.orderservice.order.repository.OrderDetailRepository;
import com.example.orderservice.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderMapper orderMapper;
    private final StoreServiceClient storeServiceClient;
    private final KafkaProducer kafkaProducer;

    public OrderResponseDTO createOrder(OrderPostDTO orderPostDTO, Long storeId, String memberId) {

        Order order = orderMapper.orderPostDTOToOrder(orderPostDTO, storeId, memberId);

        getOrderDetailsAndSaveOrderDetail(orderPostDTO, order);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderResponseDTO(savedOrder);
    }


    private void getOrderDetailsAndSaveOrderDetail(OrderPostDTO orderPostDTO, Order order) {
        int orderPrice = 0;
        for(OrderDetailPostDTO orderDetailPostDTO : orderPostDTO.getOrderDetails()) {
            ItemResponseDTO itemResponseDTO = storeServiceClient.getItem(orderDetailPostDTO.getItemId());
            OrderDetail orderDetail = orderMapper.orderDetailPostDTOToOrderDetail(orderDetailPostDTO, itemResponseDTO);
            orderPrice += orderDetail.getItemPrice() * orderDetail.getItemOrderCnt();
            OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
            order.addOrderDetail(savedOrderDetail);

            kafkaProducer.send("example-item-topic", orderDetailPostDTO);
        }
        order.setOrderPrice(orderPrice);
    }
}

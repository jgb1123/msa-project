package com.example.orderservice.messagequeue;

import com.example.orderservice.order.dto.OrderDetailPostDTO;
import com.example.orderservice.order.entity.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderDetailPostDTO send(String topic, OrderDetailPostDTO orderDetailPostDTO) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";
        try {
            jsonInString = mapper.writeValueAsString(orderDetailPostDTO);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer sent data from the Order microservice: " + orderDetailPostDTO);

        return orderDetailPostDTO;
    }
}

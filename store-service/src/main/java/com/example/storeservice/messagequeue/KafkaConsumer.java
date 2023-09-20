package com.example.storeservice.messagequeue;

import com.example.storeservice.exception.BusinessLogicException;
import com.example.storeservice.exception.ExceptionCode;
import com.example.storeservice.item.entity.Item;
import com.example.storeservice.item.repository.ItemRepository;
import com.example.storeservice.store.repository.StoreRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final ItemRepository itemRepository;

    @KafkaListener(topics = "example-item-topic")
    public void updateStockCnt(String kafkaMessage) {
        log.info("Kafka Message:->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

        Integer itemId = (Integer) map.get("itemId");
        Item item = itemRepository.findById(itemId.longValue())
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.ITEM_NOT_FOUND));

        item.setStockCnt((Integer)map.get("itemOrderCnt"));
        itemRepository.save(item);
    }
}

package com.example.storeservice.item.mapper;

import com.example.storeservice.item.dto.ItemPostDTO;
import com.example.storeservice.item.dto.ItemResponseDTO;
import com.example.storeservice.item.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public Item itemPostDTOToItem(ItemPostDTO itemPostDto) {
        return Item.builder()
                .itemName(itemPostDto.getItemName())
                .price(itemPostDto.getPrice())
                .info(itemPostDto.getInfo())
                .stockCnt(itemPostDto.getStockCnt())
                .build();
    }

    public ItemResponseDTO itemToItemResponseDTO(Item item) {
        return ItemResponseDTO.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .soldCnt(item.getSoldCnt())
                .stockCnt(item.getStockCnt())
                .info(item.getInfo())
                .build();
    }
}
